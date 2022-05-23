package com.martin.meanbean.data.sources.contract

import com.martin.meanbean.domain.models.Bean

interface MutableBeansSource {
	suspend fun getBeans(): List<Bean>
	suspend fun getBean(beanId: Int): Bean
	suspend fun addBean(bean: Bean)
	suspend fun addBeans(beans: List<Bean>)
}