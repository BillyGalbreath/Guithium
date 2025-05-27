---
title: Elements
parent: API
nav_order: 1
has_toc: true
---

## Elements

Some long and boring explanation of elements, positions, sizes, anchors, and offsets goes here.

### Try It Out!

<canvas id="canvas" width="512" height="288" style="border:1px solid"></canvas>

<div id="tryit"><table cellpadding="0" cellspacing="0"><tr>
<td><label style="color:red;">Position X</label></td>
<td><input type="range" id="posx1" min="-512" max="512" value="0" oninput="sync(this,'posx2')" /></td>
<td><input type="number" id="posx2" value="0" oninput="sync(this,'posx1')" /></td>
</tr><tr>
<td><label style="color:red;">Position Y</label></td>
<td><input type="range" id="posy1" min="-288" max="288" value="0" oninput="sync(this,'posy2')" /></td>
<td><input type="number" id="posy2" value="0" oninput="sync(this,'posy1')" /></td>
</tr><tr>
<td><label style="color:yellow;">Size X</label></td>
<td><input type="range" id="sizex1" min="0" max="100" value="50" oninput="sync(this,'sizex2')" /></td>
<td><input type="number" id="sizex2" value="50" oninput="sync(this,'sizex1')" /></td>
</tr><tr>
<td><label style="color:yellow;">Size Y</label></td>
<td><input type="range" id="sizey1" min="0" max="100" value="25" oninput="sync(this,'sizey2')" /></td>
<td><input type="number" id="sizey2" value="25" oninput="sync(this,'sizey1')" /></td>
</tr><tr>
<td><label style="color:blue;">Anchor X</label></td>
<td><input type="range" id="anchorx1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'anchorx2',2)" /></td>
<td><input type="number" id="anchorx2" value="0.50" step="0.01" oninput="sync(this,'anchorx1',2)" /></td>
</tr><tr>
<td><label style="color:blue;">Anchor Y</label></td>
<td><input type="range" id="anchory1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'anchory2',2)" /></td>
<td><input type="number" id="anchory2" value="0.50" step="0.01" oninput="sync(this,'anchory1',2)" /></td>
</tr><tr>
<td><label style="color:white;">Offset X</label></td>
<td><input type="range" id="offsetx1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'offsetx2',2)" /></td>
<td><input type="number" id="offsetx2" value="0.50" step="0.01" oninput="sync(this,'offsetx1',2)" /></td>
</tr><tr>
<td><label style="color:white;">Offset Y</label></td>
<td><input type="range" id="offsety1" min="0" max="1" value="0.5" step="0.01" oninput="sync(this,'offsety2',2);" /></td>
<td><input type="number" id="offsety2" value="0.50" step="0.01" oninput="sync(this,'offsety1,2');"  /></td>
</tr></table></div>

<script type="text/javascript">
const width = 512;
const height = 288;
function sync(input, output, x = 0) {
  let out = document.getElementById(output);
  out.value = parseFloat(input.value).toFixed(x);
  draw();
}
function draw() {
  let posx = Number.parseInt(document.getElementById("posx2").value);
  let posy = Number.parseInt(document.getElementById("posy2").value);
  let sizex = Number.parseInt(document.getElementById("sizex2").value);
  let sizey = Number.parseInt(document.getElementById("sizey2").value);
  let anchorx = Number.parseFloat(document.getElementById("anchorx2").value);
  let anchory = Number.parseFloat(document.getElementById("anchory2").value);
  let offsetx = Number.parseFloat(document.getElementById("offsetx2").value);
  let offsety = Number.parseFloat(document.getElementById("offsety2").value);
  let ax = width * anchorx;
  let ay = height * anchory;
  const canvas = document.getElementById("canvas");
  const ctx = canvas.getContext("2d");
  ctx.clearRect(0,0,width,height);
  ctx.strokeStyle="red";
  ctx.beginPath();ctx.moveTo(ax+posx,0);ctx.lineTo(ax+posx,height);ctx.stroke();
  ctx.beginPath();ctx.moveTo(0,ay+posy);ctx.lineTo(width,ay+posy);ctx.stroke();
  ctx.strokeStyle="blue";
  ctx.beginPath();ctx.moveTo(ax,0);ctx.lineTo(ax,height);ctx.stroke();
  ctx.beginPath();ctx.moveTo(0,ay);ctx.lineTo(width,ay);ctx.stroke();
  ctx.strokeStyle="#ffff00ff";
  ctx.beginPath();ctx.moveTo(ax+posx,ay+posy);ctx.lineTo(ax,ay);ctx.stroke();
  ctx.strokeStyle="white";
  ctx.fillStyle="#ffff0048";
  ctx.beginPath();ctx.rect(ax+posx-(sizex*offsetx),ay+posy-(sizey*offsety),sizex,sizey);ctx.stroke();ctx.fill();
}
draw();
</script>
