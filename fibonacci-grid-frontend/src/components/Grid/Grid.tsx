import { FC } from 'react';
import './Grid.css';
import { Box } from '@mui/material';

interface GridProps {
    gridData: number[][]
    onCellClick: (row:number, col:number) => void
}

const Grid:FC<GridProps> = ({ gridData, onCellClick }) => {
  return (
      <Box display="flex" justifyContent="center" p={2}>
        <div className="grid-container">
          {gridData.map((row, rowIndex) => (
            <div key={rowIndex} className="grid-row">
              {row.map((cell, cellIndex) => (
                <div 
                  key={cellIndex} 
                  className="grid-cell"
                  onClick={() => onCellClick(rowIndex, cellIndex)} // Capture row and column
                >
                  {cell}
                </div>
              ))}
            </div>
          ))}
        </div>
      </Box>
  );
};

export default Grid;
