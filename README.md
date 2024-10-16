# Fibonacci Grid game


🎯 Core Features: The core feature on the frontend involves designing a 50x50 grid interface. Each cell in the grid is interactive and can take on a value (the default value is 0). When a user clicks on any particular cell, the values in all the cells present in the same row and column get incremented by 1. If the cell was empty, it should now have a value of 1.

A secondary trait of the interactive grid is to identify the Fibonacci sequence. If the application detects 5 consecutive numbers from the Fibonacci sequence either horizontally or vertically, the cells carrying those values be cleared out (back to the value 0).

⚠️ Please make sure to have a way to show it working, either with logs, tests or with a small frontend.

⏰ We fully understand that many of our candidates have significant commitments and may not be able to dedicate extensive time to this coding challenge. If you find yourself constrained for time, please do not hesitate to prioritize those features and aspects of the application that best showcase your strengths, skills, and areas of expertise. In that case, feel free to list and explain potential enhancements.


# Implementation

I've used Kotlin with Spring boot as framework for the implmentaion of Backend for the Grid Application and used a small Reactjs App for presenting the inteeractive grid. Postgres have been used to persist the data for the Grid and Spring data JPA for interacrion with postgres.

I've dockerized both backend and frontend, and tried to consolidate backend, frontend and db within a docker compose.

# Steps to run 

Since everything is consolidated under one docker compose just one step will run the entire application:

`docker-compose up --build`

It docker application have all started, then visit http://localhost:3000/ to view the Grid Application.

![preview](/preview.png)


# Design pattern
* Based on the requirements I've decided to use Service Layer Repository pattern for the backend implementation.
* This pattern facilitates seperation of concerns by separrating Logic :
    
    Respository will sepearte the Data access logic 

    Service will seprate the Business logic
