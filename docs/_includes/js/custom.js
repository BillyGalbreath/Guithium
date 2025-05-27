// prevent just-the-docs from removing `href` attribute from anchor elements
const realRemoveAttr = Element.prototype.removeAttribute;
Element.prototype.removeAttribute = function (attr) {
  if (attr === 'href') {
    return;// this.setAttribute(attr, '#');
  }
  realRemoveAttr(attr);
}

// always expand main navigation for ease of use
document.addEventListener('DOMContentLoaded', function () {
  const elements = document.querySelectorAll('button[aria-pressed]');
  elements.forEach(element => {
    if (element.classList.contains('nav-list-expander')) {
      element.parentElement.classList.add('active');
      element.setAttribute('aria-pressed', 'true');
    }
  });
});

window.addEventListener('load', function() {
  document.getElementsByClassName("site-title")[0].style.opacity = 1;
});
