package com.jun.nautilus.server.jpa.repository

import com.jun.nautilus.server.jpa.entity.AppEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AppEntityRepository : JpaRepository<AppEntity,String>{
}

// 1. authentication : id & passwd
// 2. ???? :
//    - cookie : 출입증
//    - Authorization header: Authrozation:
//    jwt
//    출입증
