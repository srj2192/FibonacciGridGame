import React, { useEffect, useState } from 'react';
import GridComponent from './components/Grid/Grid';
import axios from 'axios';
import { Box, Button } from '@mui/material';


const App: React.FC = () => {

  const [gridData, setGridData] = useState([]);
  const [isUpdated, setIsUpdated] = useState(false);

  useEffect(() => {
    fetchGrid();
  }, []);

  useEffect(() => {
    if (isUpdated) {
      fetchGrid();
      setIsUpdated(false)
    }
  }, [isUpdated])

  const fetchGrid = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/grid`);
        setGridData(response.data);
    } catch (error) {
        console.error("There was an error fetching Grid !", error);
    }
  };

  const handleCellClick = async(row:number, col:number) => {
    await axios.post(`http://localhost:8080/grid/1/click/${row}/${col}`);
    setIsUpdated(true);
  };

  const handleReset = async() => {
    await axios.put(`http://localhost:8080/grid/reset/1`);
    setIsUpdated(true);
  }

  return (
    <div>
      <h1 style={{ textAlign: 'center' }}>2D Grid</h1>
      <Box display="flex" justifyContent="center">
        <Button 
          variant="contained" 
          style={{ alignContent: 'center'}}
          onClick={handleReset}>
            Reset
        </Button>
      </Box>

      <GridComponent gridData={gridData} onCellClick={handleCellClick}/>
    </div>
  );
};

export default App;
