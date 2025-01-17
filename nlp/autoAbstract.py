from textrank4zh import TextRank4Keyword, TextRank4Sentence
from my_package import my_IO
import CaseType

def keywords(text):
    """
    :param text: 待提取文本信息
    :return: 返回结果
    """
    tr4w = TextRank4Keyword()
    result = []
    tr4w.analyze(text=text, lower=True, window=2)  #文本小写，窗口为2
    for item in tr4w.get_keywords(20, word_min_len=1): #20个关键词且每个的长度最小为1
        # print(item.word, item.weight)  # word关键词 weight权重
        result.append((item.word))
    return result


def keysentence(text,n=3):

    """
    提取关键字的自动摘要
    :param text: 待提取文本信息
    :return: 返回结果
    """
    tr4s = TextRank4Sentence()
    tr4s.analyze(text=text, lower=True, source='all_filters')
    result = '' # TODO 停用部分词：XXX法院？
    for item in tr4s.get_key_sentences(num=n): #num=3表示为提取3个关键句
        # print(item.index, item.weight, item.sentence)  # index是语句在文本中位置 weight是权重 sentence摘要
        result +=  item.sentence + '\n'
    return result
#todo 缩减句子的自动摘要
def get_abstract(srcRootPath):
    '''
    :param srcRootPath:存储文件的根目录，不涉及类别
    :return:
    '''
    # 先生成目录
    for type, member in CaseType.CaseType.__members__.items():
        outRootPath='摘要/{}'.format(type)
        my_IO.mkDir(outRootPath)

    for type, member in CaseType.CaseType.__members__.items():
        outRootPath = '摘要/{}'.format(type)
        files:list=my_IO.getFileNameList('{}/{}'.format(srcRootPath, type))
        for file in files:
            abstract=keysentence(my_IO.readFile('{}/{}/{}'.format(srcRootPath, type, file)))
            my_IO.saveFile(outRootPath + '/' + file, abstract)


def sigleFileAbstract(userFilePath):
    outRootPath='摘要/用户上传'
    my_IO.mkDir(outRootPath)
    fileContent=my_IO.readFile(userFilePath)
    abstract=keysentence(fileContent)
    fileName=my_IO.getFileName(userFilePath)
    my_IO.saveFile(outRootPath+'/'+fileName,abstract)

if __name__ == '__main__':
    # 以下是生成本地已有文件摘要的代码
    # srcRootPath='../project-dev/judicature/src/main/resources/case/txt'
    # get_abstract(srcRootPath)
    # print('{} done'.format(__file__))

    # 根据用户上传文件生成摘要
    userFilePath = input()
    #userFilePath='../project-dev/judicature/src/main/resources/case/txt/adjudication/中欧汽车电器有限公司吴国琳等合伙协议纠纷股权转让纠纷其他民事民事裁定书.txt'
    sigleFileAbstract(userFilePath)