package com.leanix.app.repository

import com.leanix.app.model.Grid
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GridRepository : JpaRepository<Grid, Int>