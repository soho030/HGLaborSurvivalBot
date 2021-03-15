package de.hglabor.command.commands.doc

data class DocEntry (
    val name: String,
    val text: String,
    val keywords: ArrayList<String>
)