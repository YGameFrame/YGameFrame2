package fyy.ygame_frame.base;

/**
*<b>��ͼ��Ϣ</b>
*
*<p>
*<b>����</b>��
*����<b>�����߼�</b>���������������<b>������ͼ</b>{@link YABaseDomainView}��Ҫ�Ļ�ͼ��Ϣ��
*
*@author FeiYiyun
*
*/
public final class YDrawInformation
{
	/**<b>������ͼƬ������</b>�����ߡ��ܡ������ȡ�*/
	public final int iPicKind;
	/**<b>������ͼƬ�����</b>�����ڼ���ͼƬ��*/
	public final int iPicIndex;
	/**<b>������ͼƬ�ĺ�����</b>������Ϊ��λ��Ӧ��ע��Լ��������Ļ�׼��ΪͼƬ���ϵ��ĸ��㡣*/
	public final int iX;
	/**<b>������ͼƬ��������</b>������Ϊ��λ��Ӧ��ע��Լ��������Ļ�׼��ΪͼƬ���ϵ��ĸ��㡣*/
	public final int iY;
	/**<b>������ͼƬ�ĽǶ�</b>��ͼƬ����ת�Ƕȡ�*/
	public final float fAngle;

	/**<b>���û�ͼ��Ϣ</b>*/
	public final Object objExtra;
	/**<b>���û�ͼ��Ϣ</b>*/
	public final int iArg1;
	/**<b>���û�ͼ��Ϣ</b>*/
	public final int iArg2;

	private YDrawInformation(YDrawInfoForm drawInfoForm)
	{
		this.fAngle = drawInfoForm.fAngle;
		this.iArg1 = drawInfoForm.iArg1;
		this.iArg2 = drawInfoForm.iArg2;
		this.iPicIndex = drawInfoForm.iPicIndex;
		this.iPicKind = drawInfoForm.iPicKind;
		this.iX = drawInfoForm.iX;
		this.iY = drawInfoForm.iY;
		this.objExtra = drawInfoForm.objExtra;
	}

	/**
	*<b>��ͼ��Ϣ��</b>
	*
	*<p>
	*<b>����</b>��
	*<b>�����߼�</b>{@link YABaseDomainLogic}�������ͼ��Ҫ����Ϣ���ڴ˱�����д��Щ��Ϣ��
	*��Щ��Ϣ���ᱻ·�ɵ�<b>������ͼ</b>{@link YABaseDomainView}ȥ���ơ�
	*
	*<p>
	*<b>ע</b>��
	*���еĻ�ͼ��Ϣ<b>����</b>���Զ������
	*
	*@author FeiYiyun
	*
	*/
	public final static class YDrawInfoForm
	{
		/**<b>������ͼƬ������</b>�����ߡ��ܡ������ȡ�*/
		public int iPicKind;
		/**<b>������ͼƬ�����</b>�����ڼ���ͼƬ��*/
		public int iPicIndex;
		/**<b>������ͼƬ�ĺ�����</b>������Ϊ��λ��Ӧ��ע�������Ļ�׼��ΪͼƬ���ϵ��ĸ��㡣*/
		public int iX;
		/**<b>������ͼƬ��������</b>������Ϊ��λ��Ӧ��ע�������Ļ�׼��ΪͼƬ���ϵ��ĸ��㡣*/
		public int iY;
		/**<b>������ͼƬ�ĽǶ�</b>��ͼƬ����ת�Ƕȡ�*/
		public float fAngle;

		/**<b>���û�ͼ��Ϣ</b>*/
		public Object objExtra;
		/**<b>���û�ͼ��Ϣ</b>*/
		public int iArg1;
		/**<b>���û�ͼ��Ϣ</b>*/
		public int iArg2;

		YDrawInfoForm()
		{
		}

		YDrawInformation print()
		{
			return new YDrawInformation(this);
		}
	}
}
