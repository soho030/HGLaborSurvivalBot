package de.hglabor.command.commands.doc

abstract class DocEntry (
    val name: String,
    val text: String,
    val description: String,
    val image: String,
    val author: String,
    val keywords: ArrayList<String>
)