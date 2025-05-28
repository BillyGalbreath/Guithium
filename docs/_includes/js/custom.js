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
  document.querySelectorAll('button[aria-pressed]').forEach(el => {
    if (el.classList.contains('nav-list-expander')) {
      el.parentElement.classList.add('active');
      el.setAttribute('aria-pressed', 'true');
    }
  });
});
