// prevent just-the-docs from removing `href` attribute from anchor elements
const realRemoveAttr = Element.prototype.removeAttribute;
Element.prototype.removeAttribute = function (attr) {
  if (attr === 'href') {
    return;// this.setAttribute(attr, '#');
  }
  realRemoveAttr(attr);
}
