package com.aralb.internproject.AllDatas.UsersToDo

data class UsersToDoItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)