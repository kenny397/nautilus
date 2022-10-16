package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.server.jpa.entity.AppEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AppEntityRepository : JpaRepository<AppEntity,String>{
}


