package com.martin.meanbean.data.sources.contract

import com.martin.meanbean.domain.models.Bean

interface BeansSource {
	suspend fun getBeans(): List<Bean>
	suspend fun getBean(beanId: Int): Bean?
}

