package DataSci.judicature;

import DataSci.judicature.domain.CaseInfoSets;
import DataSci.judicature.domain.CaseMarksArr;
import DataSci.judicature.service.ExcelService;
import DataSci.judicature.service.PythonService;
import DataSci.judicature.service.WordService;
import DataSci.judicature.service.impl.ExcelServiceImpl;
import DataSci.judicature.service.impl.PythonServiceImpl;
import DataSci.judicature.service.impl.SearchServiceImpl;
import DataSci.judicature.service.impl.WordServiceImpl;
import DataSci.judicature.utils.FileUtil;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.apache.catalina.session.StandardSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootTest
public class WordTest {

    @Value("${PATH}")
    private String PATH;


    @Value("${spring.servlet.multipart.location}")
    private String location;


    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private WordServiceImpl wordService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private PythonServiceImpl pythonService;


    private static Segment nlp = null;
    private static Segment crf = null;

/*

    @Test
    void testAdjudication() throws IOException {
        String type = "adjudication";
        File dir = new File(location + "txt\\" + type);

        String[] files = dir.list();
        assert files != null;
        for (String file : files) {
            CaseMarksArr marks = wordService.extract(dir.getAbsolutePath() + "\\" + file, type);
            System.out.println(marks);
        }
    }

*/

    @BeforeAll
    static void init() throws IOException {
        nlp = NLPTokenizer.ANALYZER.enableOrganizationRecognize(true).enablePlaceRecognize(true).enableCustomDictionary(true).enableCustomDictionaryForcing(true);
        crf = new CRFLexicalAnalyzer().enablePlaceRecognize(true).enableOrganizationRecognize(true).enableCustomDictionary(true).enableCustomDictionaryForcing(true);
        BufferedReader br = new BufferedReader(new FileReader("D:\\java\\DataSci\\lqf\\JudicatureAutoLabel\\project-dev\\judicature\\src\\main\\resources\\case\\" + "tools\\dict.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() != 0) {
                CustomDictionary.remove(line);
                CustomDictionary.add(line, "accu 1024 nz 0");
            }
        }
        br.close();
        System.out.println("前奏");
    }

    @Test
    void testNLP() {
        List<Term> s = nlp.seg("　　被申请人顾爱媛，女，1970年9月29日生，汉族。");
        System.out.println(s);
        List<Term> seg1 = crf.seg("　　被申请人顾爱媛，女，1970年9月29日生，汉族。");
        System.out.println(seg1);

/*

        List<String> strings = HanLP.extractPhrase("再审申请人（一审原告、反诉被告、二审被上诉人）：史生来，男，1966年3月22日出生，汉族，住陕西省西安市户县。\n" +
                "　　被申请人（一审被告、反诉原告、二审上诉人）：甘肃省第八建设集团有限责任公司。住所地：甘肃省天水市秦州区建设路161号。", 50);
        System.out.println(strings.toString());
*/

    }

    /*

        @Test
        void testJudgment() throws IOException {
            String type = "judgment";
            File dir = new File(location + "txt\\" + type);

            String[] files = dir.list();
            assert files != null;
            for (String file : files) {
                CaseMarksArr marks = wordService.extract(dir.getAbsolutePath() + "\\" + file, type);
                System.out.println(marks);
            }
        }

        @Test
        void testMediate() throws IOException {
            String type = "mediate";
            File dir = new File(location + "txt\\" + type);

            String[] files = dir.list();
            assert files != null;
            for (String file : files) {
                CaseMarksArr marks = wordService.extract(dir.getAbsolutePath() + "\\" + file, type);
                System.out.println(marks);
            }
        }

    */
/*    @Test
    void testNotification() throws IOException {

        String type = "judgment";
        File dir = new File(location + "txt\\" + type);

        String[] files = dir.list();
        assert files != null;
        for (String file : files) {
            CaseMarksArr marks = wordService.extract(dir.getAbsolutePath() + "\\" + file);
            System.out.println(marks);
        }
    }*/

    /*
        @Test
        void testOrder() throws IOException {
            String type = "order";
            File dir = new File(location + "txt\\" + type);

            String[] files = dir.list();
            assert files != null;
            for (String file : files) {
                CaseMarksArr marks = wordService.extract(dir.getAbsolutePath() + "\\" + file, type);
                System.out.println(marks);
            }
        }

        @Test
        void testDecision() throws IOException {
            String type = "decision";
            File dir = new File(location + "txt\\" + type);

            String[] files = dir.list();
            assert files != null;
            for (String file : files) {
                CaseMarksArr marks = wordService.extract(dir.getAbsolutePath() + "\\" + file, type);
                System.out.println(marks);
            }
        }

    */
    @Test
    void testInfo() throws IOException {
        String type = "order";
        File dir = new File(location + "txt\\" + type);

        String[] files = dir.list();
        assert files != null;
        for (String file : files) {
          /*  CaseInfoSets marks = wordService.extract(dir.getAbsolutePath() + "\\" + file);
            System.out.println(marks);*/
        }
    }

    @Test
    void testFenci() throws IOException {
        String type = "adjudication";
        File dir = new File(location + "txt\\" + type);

        String[] files = dir.list();
        assert files != null;
        for (String file : files) {
            CaseMarksArr marks = wordService.extract(dir.getAbsolutePath() + "\\" + file);
            System.out.println(marks);
        }
    }

    /**
     * 贼垃圾
     */
    @Test
    void testBiaozhu() throws IOException {
        String type = "adjudication";
        File dir = new File(location + "txt\\" + type);

        String[] files = dir.list();
        assert files != null;
        BufferedReader br;
        Set<String> arr;
        for (String file : files) {
            arr = new HashSet<>();
            String encoding = fileUtil.getEncoding(new File(dir.getAbsolutePath() + "\\" + file));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(dir.getAbsolutePath() + "\\" + file), encoding));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null)
                sb.append(line);
            br.close();
            List<Term> seg = nlp.seg(sb.toString());
            for (Term term : seg) {
                if (term.nature.toString().equals("a"))
                    arr.add(term.word);
            }
            System.out.println(arr);
        }
    }


    @Test
    void proWords() throws IOException {
        File f = new File(location + "tools\\accusation.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(location + "tools\\dict.txt")));
        String line;
        while ((line = br.readLine()) != null) {
            String[] s = line.split("[\\s :]");
            for (String s1 : s) {
                if (s1.length() != 0 && !s1.equals(" "))
                    bw.write(s1 + "\r\n");
            }
        }
        bw.close();
        br.close();
    }

    @Test
    void addWords() throws IOException {
        File f = new File(location + "tools\\accusation.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(location + "tools\\dict.txt")));
        String line;
        while ((line = br.readLine()) != null) {
            String[] s = line.split("[\\s :]");
            for (String s1 : s) {
                if (s1.length() != 0 && !s1.equals(" ")) {
                    bw.write(s1 + "\r\n");
                    if (s1.contains("罪"))
                        bw.write(s1.replaceAll("罪", "") + "\r\n");
                    if (s1.contains("、"))
                        bw.write(s1.replaceAll("、", "") + "\r\n");
                    if (s1.contains("、") && s1.contains("罪"))
                        bw.write(s1.replaceAll("[、罪]", "") + "\r\n");
                }
            }
        }
        bw.close();
        br.close();
    }

    @Test
    void clear() throws IOException {
        File f = new File(location + "txt\\");
        File[] dir = f.listFiles();
        assert dir != null;
        BufferedReader br;
        BufferedWriter bw;
        StringBuffer sb;
        String line;
        for (File file : dir) {
            File[] sons = file.listFiles();
            assert sons != null;
            for (File son : sons) {
                if (son.getName().matches("(.*)FBM-CL(.*)")) {
                    br = new BufferedReader(new FileReader(son));
                    sb = new StringBuffer();
                    int i = 1;
                    while ((line = br.readLine()) != null) {
                        if (i > 3) {
                            sb.append(line).append(System.lineSeparator());
                        }

                        i++;
                    }
                    br.close();

                    bw = new BufferedWriter(new FileWriter(son));
                    bw.write(sb.toString());
                    bw.flush();
                    bw.close();
                }
            }
        }
    }

    @Test
    void tsest() {
        SearchServiceImpl s = new SearchServiceImpl();
        String s1 = s.clear("['segfile\\\\judgment\\\\李某与杨某离婚纠纷一审民事判决书.txt', 'segfile\\\\adjudication\\\\中欧汽车电器有限公司吴国琳等合伙协议纠纷股权转让纠纷其他民事民事裁定书.txt', 'segfile\\\\decision\\\\赵鹏飞合同纠纷执行决定书.txt', 'segfile\\\\mediate\\\\李某、张某离婚纠纷民事一审民事调解书(FBM-CLI-C-407872141).txt', 'segfile\\\\decision\\\\刘帅新租赁合同纠纷执行决定书.txt']");
        System.out.println(s1);
    }

    @Test
    void tsest1() {
        String s1 = excelService.proRecommend("史生来谭承天建设工程施工合同纠纷再审审查与审判监督民事裁定书");
        System.out.println(s1);
    }

    @Test
    void testpy() throws Exception {
        String path = "D:\\java\\DataSci\\lqf\\JudicatureAutoLabel\\project-dev\\judicature\\src\\main\\resources\\case\\txt\\else\\文本.txt";
        String s = pythonService.exec(path, "文本");
        System.out.println(s);
    }

    @Test
    void encoding() throws IOException {
        String path = "D:\\java\\DataSci\\lqf\\JudicatureAutoLabel\\project-dev\\judicature\\src\\main\\resources\\case\\txt\\";
        File dir = new File(path);
        for (File dirFile : dir.listFiles()) {
            for (File son : dirFile.listFiles()) {
                if (son.getName().matches("(.*)FBM(.*)")) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(son), "GBK"));
                String line;
                while ((line=br.readLine())!=null){
                    sb.append(line).append(System.lineSeparator());
                }
                br.close();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(son), StandardCharsets.UTF_8));
                bw.write(sb.toString());
                bw.close();
            }
        }
    }


}
