package de.hglabor.command.commands.doc.entries

import de.hglabor.command.commands.doc.DocEntry

object ChatEntry : DocEntry(
    "ChatSystem",
    ":bell: Wenn du jemandem im Chat erwähnen (Pingen) möchtest, kannst du einfach seinen Namen in deine Nachricht einbauen und er bekommt eine Benachrichtigung.\n :paperclips: Wenn deine Nachricht mit `;` startet, kann man diese durch einen Klick darauf in die Zwischenablage kopieren.",
    "Erhalte Informationen über das ChatSystem auf HGLaborSurvival.",
    "https://cdn.discordapp.com/attachments/804656969281175602/825640770417197086/unknown.png",
    "MooZiii",
    arrayListOf("ping", "chat", "chatsystem")
)