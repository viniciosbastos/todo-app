package com.aurelio.todo.data.mapper

interface Mapper<D, E> {
    fun fromEntity(entity: E): D
    fun fromDomain(domain: D): E
}