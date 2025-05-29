---
title: F.A.Q.
nav_order: 2
has_toc: false
questions:
  Can we <em>get rid</em> of vanilla UI elements with Guithium?: Yes! (w.i.p.)
  Some other question?: Really smart answer! \o/
  Why are there not a lot of questions here?: Because no one is asking me many questions, yet. Join me on Discord and start asking me stuff :P (invite link at the top of every page and in the left navigation)
---

# Frequently Asked Questions

Below are some of the most asked questions about Guithium.

<div class="faq">
  {% for entry in page.questions %}
  <details>
    <summary>{{ entry[0] }}</summary>
    <p>{{ entry[1] }}</p>
  </details>
  {% endfor %}
</div>
