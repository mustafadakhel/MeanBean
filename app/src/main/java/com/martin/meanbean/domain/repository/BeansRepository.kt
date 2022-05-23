package com.martin.meanbean.domain.repository

import com.martin.meanbean.domain.models.Bean

interface BeansRepository {
	suspend fun getBeans(): List<Bean>
	suspend fun getBean(beanId: Int): Bean
}