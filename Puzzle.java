package game1;

import java.util.LinkedList;
import java.util.Stack;

public class Puzzle {

	class Near {
		int row;
		int col;
		char [][]pzl;
		
		public Near()
		{
			this.row = 0;
			this.col = 0;
			this.pzl = new char[n][m];
		}
	}

	public final int n = 4;
	public final int m = 3;
	public char puzzle[][] = null;

	public Puzzle() {
		this.puzzle = new char[n][m];
		this.puzzle[0][0] = 'G';
		this.puzzle[0][1] = 'W';
		this.puzzle[0][2] = 'G';
		this.puzzle[1][0] = 'R';
		this.puzzle[1][1] = 'R';
		this.puzzle[1][2] = 'R';
		this.puzzle[2][0] = 'B';
		this.puzzle[2][1] = 'B';
		this.puzzle[2][2] = 'B';
		this.puzzle[3][0] = 'Y';
		this.puzzle[3][1] = 'Y';
		this.puzzle[3][2] = 'Y';
	}

	@Override
	public String toString() {

		String buffer = new String();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				buffer += this.puzzle[i][j] + "  ";
			}
			buffer += "\n";
		}

		return buffer;
	}
	// تابع يزيح دليل ضمن اللون في رقعة الالوان للاعلى
	public int Up(int i, int j) {

		if (i > 0) {
			return i - 1;
		}
		return -1;
	}
	// تابع يزيح دليل ضمن اللون في رقعة الالوان للاسفل
	public int Down(int i, int j) {

		if (i < this.n - 1) {
			return i + 1;
		}
		return -1;
	}
	// تابع يزيح دليل ضمن اللون في رقعة الالوان لليسار
	public int Left(int i, int j) {

		if (j > 0) {
			return j - 1;
		}
		return -1;
	}
	// تابع يزيح دليل ضمن اللون في رقعة الالوان لليمين
	public int Right(int i, int j) {

		if (j < this.m - 1) {
			return j + 1;
		}
		return -1;
	}

	// تابع يجلب عدد عناصر المجاورات الخاصة بلون ما
	public int getNeighboursCount(int i, int j) {

		int counter = 0;

		if (this.Up(i, j) != -1)
			++counter;
		if (this.Down(i, j) != -1)
			++counter;
		if (this.Left(i, j) != -1)
			++counter;
		if (this.Right(i, j) != -1)
			++counter;

		return counter;
	}

	// تابع ياخذ نسخة من رقعة الالوان
	// يحرك الخلية البيضاء الى الاعلى
	// يعيد الخلية بعد التحريك الى الاعلى
	public Near MoveUp(int i, int j) {

		Near near = new Near();
		
		near.pzl= this.puzzle;

		if (this.Up(i, j) != -1) {

			char temp = near.pzl[i][j];
			near.pzl[i][j] = near.pzl[this.Up(i, j)][j];
			near.pzl[this.Up(i, j)][j] = temp;
			
			near.row = this.Up(i, j);
			near.col = j;
			
			return near;
		}
		return null;
	}
	
	// تابع ياخذ نسخة من رقعة الالوان
	// يحرك الخلية البيضاء الى اليمين
	// يعيد الخلية بعد التحريك الى اليمين
	public Near MoveRight(int i, int j) {
		
		Near near = new Near();

		near.pzl= this.puzzle;
		
		if (this.Right(i, j) != -1) {

			char temp = near.pzl[i][j];
			near.pzl[i][j] = near.pzl[i][this.Right(i, j)];
			near.pzl[i][this.Right(i, j)] = temp;

			near.row = i;
			near.col = this.Right(i, j);

			return near;
		}
		return null;
	}
	
	// تابع ياخذ نسخة من رقعة الالوان
	// يحرك الخلية البيضاء الى الاسفل
	// يعيد الخلية بعد التحريك الى الاسفل
	public Near MoveDown(int i, int j) {
		
		Near near = new Near();

		near.pzl= this.puzzle;

		if (this.Down(i, j) != -1) {

			char temp = near.pzl[i][j];
			near.pzl[i][j] = near.pzl[this.Down(i, j)][j];
			near.pzl[this.Down(i, j)][j] = temp;

			near.row = this.Down(i, j);
			near.col = j;

			return near;
		}
		return null;
	}

	// تابع ياخذ نسخة من رقعة الالوان
	// يحرك الخلية البيضاء الى اليسار
	// يعيد الخلية بعد التحريك الى اليسار
	public Near MoveLeft(int i, int j) {
		Near near = new Near();
		near.pzl = this.puzzle;

		if (this.Left(i, j) != -1) {

			char temp = this.puzzle[i][j];
			this.puzzle[i][j] = this.puzzle[i][this.Left(i, j)];
			this.puzzle[i][this.Left(i, j)] = temp;

			near.row = i;
			near.col = this.Left(i, j);

			return near;
		}
		return null;
	}
	// تابع ياخذ موقع لون من صفوفة الالوان ويجلب كل مجاوراتة من الاعلى واليمين والاسفل واليسار
	public char[] Neighbours(int i, int j) {

		char neighbours[] = new char[getNeighboursCount(i, j)];
		int index = 0;

		if (this.Up(i, j) != -1) {

			neighbours[index] = this.puzzle[this.Up(i, j)][j];
			++index;
		}
		if (this.Right(i, j) != -1) {
			
			neighbours[index] = this.puzzle[i][this.Right(i, j)];
			index++;
		}
		if (this.Down(i, j) != -1) {
			
			neighbours[index] = this.puzzle[this.Down(i, j)][j];
			index++;
		}
		if (this.Left(i, j) != -1) {

			neighbours[index] = this.puzzle[i][this.Left(i, j)];
			index++;
		}
		return neighbours;
	}
	
	// تابع مساعد في اتمام تابع التحقق من الوصول الى الحل النهائي
	// يأخذ مصفوفة المجاورات التي تمثل الالوان المجاورة للون الحالي 
	// ويأخذ اللون الحالي 
	// واذا كان هناك لون مجاورات اللون الحالي يشابهه يرجع قيمة خاطئة
	public boolean isEqual(char neighbours[], char Color) {

		for (int i = 0; i < neighbours.length; i++)
			if (Color == neighbours[i])
				return false;

		return true;
	}
	// تابع يتحقق من الوصول الى الحل النهائي للرقعة
	public boolean IsSolved(char [][]pzl) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				char near[] = Neighbours(i, j);
				if (isEqual(near, pzl[i][j]) == false)
					return false;
			}
		}

		return true;
	}
/*
	// خوارزمية البحث بالعمق اولا
	void DFS(int i, int j) {

		if (IsSolved(this.puzzle)) {
			System.out.println("DFS Solved!");
		}

		Near near = new Near();
		near.row = i;
		near.col = j;
		near.pzl = this.puzzle;
		

		// انشاء مكدس لاتمام مراحل تنفيذ خوارزمية البحث 
        Stack<Near> stack = new Stack<>();
		
        // اضافة عقدة البداية الى قمة المكدس
        stack.push(near); 
		
        while(stack.empty() == false) 
        {
            near = stack.peek(); 
            stack.pop(); 
            
			if (!IsSolved(near.pzl) && Up(near.row, near.col) != -1 ) {
				stack.push(MoveUp(near.row, near.col));
			}
			if (!IsSolved(near.pzl) && Right(near.row, near.col) != -1) {
				stack.push(MoveRight(near.row, near.col));
			}

			if (!IsSolved(near.pzl) && Down(near.row, near.col) != -1) {
				stack.push(MoveDown(near.row, near.col));
			}

			if (!IsSolved(near.pzl) && Left(near.row, near.col) != -1) {
				stack.push(MoveLeft(near.row, near.col));
			}
			if (IsSolved(near.pzl)) {
				System.out.println("DFS Solved!");
				break;
			}
        } 
	}
*/
	// خوارزمية البحث بالعرض اولا
	void BFS(int i, int j) {

		if (IsSolved(this.puzzle)) {
			System.out.println("Solved");
		}

		Near near = new Near();
		near.row = i;
		near.col = j;
		near.pzl = this.puzzle;

		// انشاء سلسلة ومعاملتها معاملة رتل من اجل اتمام مراحل خوارزمية البحث
		LinkedList<Near> queue = new LinkedList<Near>();
		queue.add(near);

		while (queue.size() != 0) {
			near = queue.poll();

			// System.out.print(near.value + " ");

			if (!IsSolved(near.pzl) && Up(near.row, near.col) != -1) {
				queue.add(MoveUp(near.row, near.col));
			}
			if (!IsSolved(near.pzl) && Right(near.row, near.col) != -1) {
				queue.add(MoveRight(near.row, near.col));
			}

			if (!IsSolved(near.pzl) && Down(near.row, near.col) != -1) {
				queue.add(MoveDown(near.row, near.col));
			}

			if (!IsSolved(near.pzl) && Left(near.row, near.col) != -1) {
				queue.add(MoveLeft(near.row, near.col));
			}
			if (IsSolved(near.pzl)) {
				System.out.println("BFS Solved!");
				break;
			}
		}
	}

	public static void main(String[] args) {

		Puzzle pzl = new Puzzle();

		// طباعة الرقعة
		System.out.println(pzl);
		System.out.println("--------------------------");
		
		// وقت بداية التنفيذ
		long startTime = System.nanoTime();
		
		pzl.BFS(0, 1);
		
		// وقت نهاية التنفيذ
		long endTime = System.nanoTime();
		
		// وقت تنفيذ خوارزمية البحث بالعرض أولا
		long totalTime = endTime - startTime;
		
		System.out.println("--------------------------");
		// طباعة وقت التنفيذ
		System.out.println("Total Excecution Time: " + totalTime + " NS");
		
		System.out.println("--------------------------");
		// طباعة الرقعة
		System.out.println(pzl);
	}

}
