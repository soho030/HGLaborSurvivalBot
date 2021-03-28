package de.hglabor.command.commands.doc.entries

import de.hglabor.command.commands.doc.DocEntry

object CombatLogEntry : DocEntry(
    "Anti-CombatLogging",
    "Dieses Feature verbietet es, sich im Fight auszuloggen. Sobald du einen Spieler schlägst seid ihr beide in einer 5 sekündigen Kampfphase. Sollte sich einer von euch beiden während dieser Kampfphase ausloggen, stirbt er.",
    "Dieses Feature verbietet es, sich im Fight auszuloggen.",
    "https://i.imgur.com/ldTLPzH.png",
    "MooZiii",
    arrayListOf("combatlogging", "anticombatlogging")
)