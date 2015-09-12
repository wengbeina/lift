package test;

public class CasTest {
public static void main(String[] args) {
TriFunc tri = new TriFunc(); 

// 生成一块25×100的画布
Canvas canvas = new Canvas(25, 120); 

// 画sin曲线，周期为2
tri.drawSin(canvas, 2.0); 
canvas.printCanvas(); 

System.out.println(); 
canvas.reset(); 
// 画cos曲线，周期为2
tri.drawCos(canvas, 2.0); 
canvas.printCanvas(); 
}
}

class TriFunc {

/**
* 画sin曲线
* @param canvas 画布
* @param period 曲线周期
*/
public void drawSin(Canvas canvas, double period) { 
char[][] chars = canvas.getCanvas(); 
// x 轴的比率
double xRatio = (2 * period * Math.PI) / (canvas.getWidth() - 1); 
// y 轴的放大倍率
int yMulti = (canvas.getHeight() - 1) / 2; 
for(int i = 0; i < canvas.getWidth(); i++) {
// 将数组索引映射为横坐标值
double k = (i - canvas.getWidth() / 2) * xRatio; 
// 将sin值映射为数组索引
int h = yMulti - (int)Math.round(Math.sin(k) * yMulti); 
chars[h][i] = Canvas.FILL_CHAR; 
}
}

/**
* 画cos曲线
* @param canvas 画布
* @param period 曲线周期
*/
public void drawCos(Canvas canvas, double period) {
char[][] chars = canvas.getCanvas(); 
double xRatio = (2 * period * Math.PI) / (canvas.getWidth() - 1); 
int yMulti = (canvas.getHeight() - 1) / 2; 
for(int i = 0; i < canvas.getWidth(); i++) {
double k = (i - canvas.getWidth() / 2) * xRatio; 
int h = yMulti - (int)Math.round(Math.cos(k) * yMulti); 
chars[h][i] = Canvas.FILL_CHAR; 
}
}
}


class Canvas {

private int height; 
private int width; 
private char[][] canvas; 

// 填充字符
public static char FILL_CHAR = '.'; 
// 空白字符
public static char BLANK_CHAR = ' '; 

/**
* 构建一块画布
* @param height
* @param width
*/
public Canvas(int height, int width) {
// 由于需要画坐标轴，所以得采用奇数
this.height = height % 2 == 0 ? height + 1 : height; 
this.width = width % 2 == 0 ? width + 1 : width; 
init(); 
}

/**
* 初始化画布
*/
private void init() {
this.canvas = new char[height][width]; 
for(int i = 0; i < height; i++) {
for(int j = 0; j < width; j++) {
canvas[i][j] = BLANK_CHAR; 
}
}
addAxis(); 
}

/**
* 添加坐标轴
*/
private void addAxis() {
// 添加横坐标
int y = height / 2; 
for(int x = 0; x < width; x++) {
canvas[y][x] = '-'; 
}
// 添加纵坐标
int xx = width / 2; 
for(int yy = 0; yy < height; yy++) {
canvas[yy][xx] = '|'; 
}
// 添加原点
canvas[y][xx] = '+'; 
}

/**
* 输出画布
*/
public void printCanvas() {
for(int i = 0; i < height; i++) {
for(int j = 0; j < width; j++) {
System.out.print(canvas[i][j]); 
}
System.out.println(); 
}
}

/**
* 清空画布
*/
public void reset() {
init(); 
}

public int getHeight() {
return height; 
}
public int getWidth() {
return width; 
}

public char[][] getCanvas() {
return canvas; 
} 
}