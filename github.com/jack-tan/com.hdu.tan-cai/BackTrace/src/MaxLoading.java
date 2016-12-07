
public class MaxLoading {
	
	static int n;//集装箱数目
	static int[] w;//集装箱重量数组
	static int c;//第一艘轮船的载重量
	static int cw;//当前载重量
	static int bestw;//当前最优载重量
	static int r;//剩余集装箱重量
	static int[] x;//当前解,记录从根节点到当前节点的路径
	static int[] bestx;//记录当前的最优解
	/**
	 * @param ww集装箱重量数组
	 * @param cc当前载重量
	 * @return 计算出的最优解
	 */
	public static int maxLoading(int[] ww,int cc){
		//初始化数据成员，数组的下标从1开始
		n=ww.length-1;//当n=ww.length的时候刚好是叶子节点
		w=ww;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		c=cc;
		cw=0;
		bestw=0;
		x=new int[n+1];
		bestx=new int[n+1];
		
		//初始化r，剩余的r就是开始的最大重量
		for(int i=0;i<=n;i++){
			r+=w[i];
		}
		
		backTrace(1);
		return bestw;
	}
	//递归回溯
   public static void backTrace(int i) {
		if(i>n){//到达叶子节点
			if(cw>bestw){
				for(int j=1;j<=n;j++){
					bestx[j]=x[j];
				}
				bestw=cw;
			}
			return ;
		} 
		
		r-=w[i];
		if(cw+w[i]<=c){//搜索左子树(约束函数)
			x[i]=1;//左子树的标志位1
			cw+=w[i];
			backTrace(i+1);
			cw-=w[i];//回复现场，因为cw是全局变量
		}
		if(cw+r>bestw){//搜索右子树(限界函数)
			x[i]=0;
			backTrace(i+1);
		}
		r+=w[i];//恢复现场，r为全局变量
	}
public static void main(String[] args) {
	int[] w={0,10,40,40};
	int c1=50;
	int c2=50;
	int n=w.length-1;
	int weight1=maxLoading(w, c1);
	int weight2=0;//保存第二艘船可能要装的重量
	for(int i=1;i<=n;i++)
		weight2+=w[i]*(1-bestx[i]);//剩余未装入集装箱的全部装入第二艘船,bestx[i]为1或者0
		if(weight2>c2)
			System.out.println("无法将全部集装箱全部装入两艘船");
		else{
			System.out.println("第一艘船装入的重量是:"+weight1);
			System.out.println("第二艘船装入的重量是:"+weight2);
			for(int i=1;i<=n;i++){
				if(bestx[i]==1)
					System.out.println("集装箱"+i+"装入第一艘船");
				else
					System.out.println("集装箱"+i+"装入第二艘船");
			}
		}
}
}
