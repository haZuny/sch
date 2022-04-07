//���� Ʈ����� ����ȭ�� ���� ���α׷�
// �ΰ��� ȭ�� ��, oldmaster.txt �� transaction.txt �ΰ��� �о�
//���ŵ� ���ο� ������ȭ�� newmaster.txt �� report.txt �� �����Ѵ�

#include <stdio.h>
#include < string.h >
#define SENTINEL 100   //ȭ���� ���� �˸��� Ư�� Ű��

//��ǰ ���ڵ� ����ü
struct product_rec {
	int id;
	char name[20];
	int inventory;
};

//���ŷ��ڵ� ����ü
struct update_rec {
	char update_code;
	int id;
	char name[20];
	int inventory;
};

//�������� ����

FILE* old_master, * transaction, * new_master, * report;
struct product_rec old_rec, buffer_rec;   //buffer_rec�� �� ���ڵ带 ���� �� �����
struct update_rec trans_rec;
int add_cnt = 0, change_cnt = 0, delete_cnt = 0, error_cnt = 0;
int delete_record;
char contents[100];
//�Լ� ���� 

void Get_Old()  //Old Master ȭ�Ͽ��� �о����
{
	fscanf(old_master, "\n%d %s %d", &old_rec.id, old_rec.name, &old_rec.inventory);
}

void Get_Transaction()  //Transaction ȭ�Ͽ��� �о����
{
	fscanf(transaction, "\n%c %d %s %d", &trans_rec.update_code, &trans_rec.id, trans_rec.name, &trans_rec.inventory);
}

void Put_Old_To_New()  //Old Master ���ڵ带 �״�� New Master �� ���
{
	fprintf(new_master, "%d %s %d\n", old_rec.id, old_rec.name, old_rec.inventory);
}

void Put_buffer_To_New()  //���� ���� ���ڵ�, ��, �߰��� ���ڵ带 New Master�� ���
{
	fprintf(new_master, "%d %s %d\n", buffer_rec.id, buffer_rec.name, buffer_rec.inventory);
}

void Set_New_Record()    //Transaction ���ڵ带 ������ ���ڵ带 ���� �����
{
	buffer_rec.id = trans_rec.id;
	strcpy(buffer_rec.name, trans_rec.name);
	buffer_rec.inventory = trans_rec.inventory;
}


void Error_Print(char err_code)  //�������� ��� 
{
	switch (err_code)
	{
	case 'A':
		fprintf(report, "%c %d %s %d : �߰�����\n", trans_rec.update_code, trans_rec.id, trans_rec.name, trans_rec.inventory);
		break;
	case 'C':
		fprintf(report, "%c %d %s %d : �ش� ���ڵ� ����\n", trans_rec.update_code, trans_rec.id, trans_rec.name, trans_rec.inventory);
		break;
	case 'E':
		fprintf(report, "%c %d %s %d : �����ڵ����\n", trans_rec.update_code, trans_rec.id, trans_rec.name, trans_rec.inventory);
		break;
	}
	error_cnt++;

}


//�����Լ� ����

int main(void)
{

	old_master = fopen("oldmaster.txt", "r");
	transaction = fopen("transaction.txt", "r");
	new_master = fopen("newmaster.txt", "w");
	report = fopen("report.txt", "w");

	// ù ���ڵ� �б�
	Get_Old();
	Get_Transaction();

	//��Ƽ�� ���� ������ �ݺ� ����
	while (!(old_rec.id == SENTINEL || trans_rec.id == SENTINEL))
	{
		if (old_rec.id < trans_rec.id)
		{
			Put_Old_To_New();
			Get_Old();
		}

		else if (old_rec.id == trans_rec.id)
		{
			switch (trans_rec.update_code)
			{
			case 'A':
				Error_Print('A');
				Get_Transaction();
				break;
				//
				//
				//
			case 'C':
				old_rec.inventory += trans_rec.inventory;
				change_cnt++;
				Get_Transaction();
				//
				//
				//
				break;
			case 'D':
				delete_cnt++;
				Get_Old();
				Get_Transaction();
				//
				//
				//
				break;
			default:
				Error_Print('E');
				error_cnt++;
				break;
			}
		}
		else // old_rec.id > trans_rec.id �� ���
		{
			switch (trans_rec.update_code)
			{
			case 'A':
				Set_New_Record();
				Get_Transaction();
				///
				///
				///
				while (trans_rec.id != SENTINEL && trans_rec.id == buffer_rec.id && delete_record == 0)
				{
					switch (trans_rec.update_code)
					{
					case 'A':
						Error_Print('A');
						break;
					case 'C':
						buffer_rec.inventory += trans_rec.inventory;  //����� ���� �ʰ� �����ͷ��ڵ� ���游 ����
						change_cnt++;
						break;
					case 'D':
						delete_record = 1;
						delete_cnt++;
						break;
					default:
						Error_Print('E');
						break;
					}
					Get_Transaction();
				}
				if (delete_record == 0)
					Put_buffer_To_New();
				add_cnt++;
				break;
			case 'C':
			case 'D':
				Error_Print('C');
				Get_Transaction();
				break;
			default:
				Error_Print('E');
				Get_Transaction();
				break;
			}
		}
	}

	//===================================
	//��Ƽ���� ���� ��� �ܿ����ڵ� ó��
	//====================================

	if (trans_rec.id == SENTINEL)  // Transaction���� ���� ��Ƽ���� ���� ���
	{
		while (old_rec.id != SENTINEL)
		{
			Put_Old_To_New();
			Get_Old();
		}
		fprintf(new_master, "%d %s %d\n", SENTINEL, "*", 0);

	}
	else      // Old Master���� ���� ��Ƽ���� ���� ���
	{
		while (trans_rec.id != SENTINEL)
		{

			switch (trans_rec.update_code)
			{
			case 'A':
				Set_New_Record();
				delete_record = 0;
				Get_Transaction();
				while (trans_rec.id != SENTINEL && trans_rec.id == buffer_rec.id && delete_record == 0)
				{
					switch (trans_rec.update_code)
					{
					case 'A':
						Error_Print('A');
						break;
					case 'C':
						buffer_rec.inventory += trans_rec.inventory; //����� ���� �ʰ� �����ͷ��ڵ� ���游 ����
						change_cnt++;
						break;
					case 'D':
						delete_record = 1;
						delete_cnt++;
						break;
					default:
						Error_Print('E');
						break;
					}
					Get_Transaction();
				}
				if (delete_record == 0)
					Put_buffer_To_New();
				add_cnt++;
				break;
			case 'C':
			case 'D':
				Error_Print('E');
				Get_Transaction();
				break;
			default:
				Error_Print('E');
				Get_Transaction();
				break;
			}
		}
	}

	//������
	//��Ƽ�η��ڵ� ���
	fprintf(new_master, "%d %s %d\n", SENTINEL, "*", 0);
	//����Ʈȭ�� ���
	fprintf(report, "==========\n");
	fprintf(report, "���� ��� \n");
	fprintf(report, "==========\n");

	fprintf(report, "�߰� Ƚ�� : %d\n", add_cnt);
	fprintf(report, "���� Ƚ�� : %d\n", change_cnt);
	fprintf(report, "���� Ƚ�� : %d\n", delete_cnt);
	fprintf(report, "���� Ƚ�� : %d\n", error_cnt);

	fclose(old_master);
	fclose(transaction);
	fclose(new_master);
	fclose(report);

	// New Master�� Reportȭ�� �������

	new_master = fopen("newmaster.txt", "r");
	while (fgets(contents, 100, new_master) != NULL)
	{
		fputs(contents, stdout);
	}
	fclose(new_master);

	printf("\n");

	report = fopen("report.txt", "r");
	while (fgets(contents, 100, report) != NULL)
	{
		fputs(contents, stdout);
	}

	fclose(report);

	system("pause");
}
