package fyy.ygame_frame.extra;

import java.util.Map;

import fyy.ygame_frame.base.YABaseDomainLogic;
import fyy.ygame_frame.base.YDrawInformation.YDrawInfoForm;

/**
 * <b>����ӿ�</b>
 * 
 * @author fei yiyun
 * 
 */
/**
*<b>����</b>
*
*<p>
*<b>����</b>��
*<b>�����߼�</b>{@link YABaseDomainLogic}ÿ�غϿ��ܱ�ִ�е�������Ҫ��<b>�����߼�</b>
*�ύ���ܱ�ִ�е�����
*
*<p>
*<b>����</b>��
*<li>ʵ�ָýӿڵ���Ӧ�ò��ع�����
*<li>��<b>�����߼�</b>����ͬһ�����¡�
*
*<p>
*<b>��ϸ</b>��
*�μ�{@link #execute(YDrawInfoForm, Map)}��
*
*@author FeiYiyun
*
*/
public interface YITask
{
	/**ִ������������Ҫ���Ĺ���Ϊ���ı�<b>�����߼�</b>
	*{@link YABaseDomainLogic}��Ӧ���߼�ֵ�������ͼ�õ�����Ϣ������д��
	*<b>��ͼ��Ϣ��</b>{@link YDrawInfoForm}��
	*@param drawInfoForm <b>��ͼ��Ϣ��</b>
	*@param domainLogics <b>�����߼�</b>�б�
	*/
	void execute(YDrawInfoForm drawInfoForm,
	// domainLogics�����У��������ڵ�ͼ��ִ���߶������˱���֪����ͼ��Ϣ
			Map<Integer, YABaseDomainLogic<?>> domainLogics);
}
