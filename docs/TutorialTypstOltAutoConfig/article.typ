#import "@preview/physica:0.8.0"

#let article(
  title: "INTELBRAS",
  subtitle: none,
  authors: (""),
  date: 2024-1,
  doc,
) = {
  // Define metadados do documento
  set document(title: title, author: authors)

  set page(
    numbering: "1",
    paper: "a4",
    background:  place(image("assets/intelbras-header.png")),
    margin: (top: 3cm, bottom: 2cm, left: 2.5cm, right: 2cm),
  )
  set text(size: 12pt, font: "STIX Two Text")
  set par(
    first-line-indent: 0.5cm,
    justify: true,
    leading: 0.65em,
    linebreaks: "optimized",
  )
  set heading(numbering: "1.a)")
  set math.equation(numbering: "(1)")

  align(horizon + center)[
    #text(20pt, title, weight: "bold")
    #v(1em)
    #text(subtitle, weight: "regular")
  ]

  align(bottom + left)[
    #text(list(..authors, marker: "", body-indent: 0pt), weight: "semibold")
    #text(date)
  ]

  pagebreak()

  show outline.entry.where(level: 1): it => {
    strong(it)
  }

  outline(title: [Sumário #v(1em)], indent: 2em)

  pagebreak()

  doc
}

#let remark(body) = {
  align(center)[
    #v(1em)
    #block(
      inset: 16pt,
      stroke: 1pt,
      radius: 5pt,
      width: 95%
    )[
      #set text(size: 0.9em)
      #set align(left)
      _Resolução_: #body
    ]
  ]
}

#let highlighted(body) = {
  align(center)[
    #block(
      fill: luma(230),
      inset: 1em,
      radius: 1em,
      width: 75%,
      body,
    )
  ]
}


#let remark_text(body) = {
  align(center)[
    #v(1em)
    #block(
      inset: 16pt,
      stroke: 1pt,
      radius: 5pt,
      width: 98%
    )[
      #set text(size: 0.9em)
      #set align(left)
      #body
    ]
  ]
}