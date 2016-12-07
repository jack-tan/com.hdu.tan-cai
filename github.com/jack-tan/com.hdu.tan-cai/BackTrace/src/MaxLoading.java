
public class MaxLoading {
	
	static int n;//��װ����Ŀ
	static int[] w;//��װ����������
	static int c;//��һ���ִ���������
	static int cw;//��ǰ������
	static int bestw;//��ǰ����������
	static int r;//ʣ�༯װ������
	static int[] x;//��ǰ��,��¼�Ӹ��ڵ㵽��ǰ�ڵ��·��
	static int[] bestx;//��¼��ǰ�����Ž�
	/**
	 * @param ww��װ����������
	 * @param cc��ǰ������
	 * @return ����������Ž�
	 */
	public static int maxLoading(int[] ww,int cc){
		//��ʼ�����ݳ�Ա��������±��1��ʼ
		n=ww.length-1;//��n=ww.length��ʱ��պ���Ҷ�ӽڵ�
		w=ww;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		c=cc;
		cw=0;
		bestw=0;
		x=new int[n+1];
		bestx=new int[n+1];
		
		//��ʼ��r��ʣ���r���ǿ�ʼ���������
		for(int i=0;i<=n;i++){
			r+=w[i];
		}
		
		backTrace(1);
		return bestw;
	}
	//�ݹ����
   public static void backTrace(int i) {
		if(i>n){//����Ҷ�ӽڵ�
			if(cw>bestw){
				for(int j=1;j<=n;j++){
					bestx[j]=x[j];
				}
				bestw=cw;
			}
			return ;
		} 
		
		r-=w[i];
		if(cw+w[i]<=c){//����������(Լ������)
			x[i]=1;//�������ı�־λ1
			cw+=w[i];
			backTrace(i+1);
			cw-=w[i];//�ظ��ֳ�����Ϊcw��ȫ�ֱ���
		}
		if(cw+r>bestw){//����������(�޽纯��)
			x[i]=0;
			backTrace(i+1);
		}
		r+=w[i];//�ָ��ֳ���rΪȫ�ֱ���
	}
public static void main(String[] args) {
	int[] w={0,10,40,40};
	int c1=50;
	int c2=50;
	int n=w.length-1;
	int weight1=maxLoading(w, c1);
	int weight2=0;//����ڶ��Ҵ�����Ҫװ������
	for(int i=1;i<=n;i++)
		weight2+=w[i]*(1-bestx[i]);//ʣ��δװ�뼯װ���ȫ��װ��ڶ��Ҵ�,bestx[i]Ϊ1����0
		if(weight2>c2)
			System.out.println("�޷���ȫ����װ��ȫ��װ�����Ҵ�");
		else{
			System.out.println("��һ�Ҵ�װ���������:"+weight1);
			System.out.println("�ڶ��Ҵ�װ���������:"+weight2);
			for(int i=1;i<=n;i++){
				if(bestx[i]==1)
					System.out.println("��װ��"+i+"װ���һ�Ҵ�");
				else
					System.out.println("��װ��"+i+"װ��ڶ��Ҵ�");
			}
		}
}
}
