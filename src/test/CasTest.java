package test;

public class CasTest {
public static void main(String[] args) {
TriFunc tri = new TriFunc(); 

// ����һ��25��100�Ļ���
Canvas canvas = new Canvas(25, 120); 

// ��sin���ߣ�����Ϊ2
tri.drawSin(canvas, 2.0); 
canvas.printCanvas(); 

System.out.println(); 
canvas.reset(); 
// ��cos���ߣ�����Ϊ2
tri.drawCos(canvas, 2.0); 
canvas.printCanvas(); 
}
}

class TriFunc {

/**
* ��sin����
* @param canvas ����
* @param period ��������
*/
public void drawSin(Canvas canvas, double period) { 
char[][] chars = canvas.getCanvas(); 
// x ��ı���
double xRatio = (2 * period * Math.PI) / (canvas.getWidth() - 1); 
// y ��ķŴ���
int yMulti = (canvas.getHeight() - 1) / 2; 
for(int i = 0; i < canvas.getWidth(); i++) {
// ����������ӳ��Ϊ������ֵ
double k = (i - canvas.getWidth() / 2) * xRatio; 
// ��sinֵӳ��Ϊ��������
int h = yMulti - (int)Math.round(Math.sin(k) * yMulti); 
chars[h][i] = Canvas.FILL_CHAR; 
}
}

/**
* ��cos����
* @param canvas ����
* @param period ��������
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

// ����ַ�
public static char FILL_CHAR = '.'; 
// �հ��ַ�
public static char BLANK_CHAR = ' '; 

/**
* ����һ�黭��
* @param height
* @param width
*/
public Canvas(int height, int width) {
// ������Ҫ�������ᣬ���Եò�������
this.height = height % 2 == 0 ? height + 1 : height; 
this.width = width % 2 == 0 ? width + 1 : width; 
init(); 
}

/**
* ��ʼ������
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
* ���������
*/
private void addAxis() {
// ��Ӻ�����
int y = height / 2; 
for(int x = 0; x < width; x++) {
canvas[y][x] = '-'; 
}
// ���������
int xx = width / 2; 
for(int yy = 0; yy < height; yy++) {
canvas[yy][xx] = '|'; 
}
// ���ԭ��
canvas[y][xx] = '+'; 
}

/**
* �������
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
* ��ջ���
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