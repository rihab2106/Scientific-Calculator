import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    // الذي سيمثل نافذة البرنامج نفسه لكي نستطيع الوصول إليه من داخل أي دالة نقوم بإنشائها في البرنامج Stage قمنا بتعريف كائن الـ
    Stage myStage;

    // هنا قمنا بإنشاء جميع الأزرار و مربعات النصوص التي سنضعها في النافذة
    Pane root = new Pane();
    Button b0 = new Button("0");
    Button b1 = new Button("1");
    Button b2 = new Button("2");
    Button b3 = new Button("3");
    Button b4 = new Button("4");
    Button b5 = new Button("5");
    Button b6 = new Button("6");
    Button b7 = new Button("7");
    Button b8 = new Button("8");
    Button b9 = new Button("9");
    Button comma = new Button(".");
    Button plus = new Button("+");
    Button minus = new Button("-");
    Button multiple = new Button("×");
    Button divide = new Button("÷");
    Button cos = new Button("cos");
    Button sin = new Button("sin");
    Button tan = new Button("tan");
    Button sqrt = new Button("√");
    Button power = new Button("^");
    Button modulo = new Button("%");
    Button exponential = new Button("e");
    Button pi = new Button("π");
    Button parentesesLeft = new Button("(");
    Button parentesesRight = new Button(")");
    Button equal = new Button("=");
    Button clear = new Button("C");
    Button back = new Button("←");
    TextField textField = new TextField("");
    TextArea historyText = new TextArea();

    // هنا قمنا بإنشاء شريط القوائم, القوائم و العناصر التي بداخلهم
    MenuBar menuBar = new MenuBar();
    Menu view = new Menu(" View ");
    Menu edit = new Menu(" Edit ");
    Menu help = new Menu(" Help ");
    CheckMenuItem history = new CheckMenuItem("History");
    MenuItem copy = new MenuItem("Copy");
    MenuItem paste = new MenuItem("Paste");
    MenuItem copyHistory = new MenuItem("Copy History");
    MenuItem clearHistory = new MenuItem("Clear History");
    MenuItem keyboardShortcuts = new MenuItem("Keyboard Shortcuts");
    MenuItem about = new MenuItem("About");

    // لإخفاء الأصفار التي لا حاجة إلى ظهورها format سنستخدم الكائن
    DecimalFormat format = new DecimalFormat("0.###############");

    // لعرض النوافذ المنبثقة التي ستظهر عندما يقوم المستخدم بالنقر على الخيارات التي وضعناها في شريط القوائم alert سنستخدم الكائن
    Alert alert = new Alert(AlertType.INFORMATION);


    // سيتم إستدعاء هذه الدالة في كل مرة قبل إدخال أي رقم, حرف أو رمز لتعديل نص المعادلة بشكل تلقائي عند الحاجة
    // و هي تقوم بمقارنة آخر حرف مدخل في مربع النص مع الشيء الذي سيقوم المستخدم بإدخاله
    private void autoAddOrRemove(String button) {

        // lastCharacter إذا لم يكن مربع النص فارغاً سيتم تخزين آخر رقم, رمز أو حرف ظاهر في مربع النص في الكائن
        if (!textField.getText().isEmpty()) {
            Character lastCharacter = textField.getText().charAt(textField.getText().length() - 1);

            switch (button) {

                // هو أحد الإحتمالات التالية, سيتم إضافة ×, ×0 أو ) بعدهlastCharacter إذا تم النقر على رمز و كان الشيء المخزن في الكائن
                case "symbol":
                    switch (lastCharacter) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case 'e':
                        case 'π':
                            textField.setText(textField.getText() + "×");
                            break;
                        case '.':
                            textField.setText(textField.getText() + "0×");
                            break;
                    }
                    break;

                // سيتم إضافة الرمز × بعده ,π أو e هو lastCharacter إذا تم النقر على رقم و كان الشيء المخزن في الكائن
                // عبارة عن 0 لا حاجة له سيتم مسحه فقط lastCharacter و إذا تم النقر على رقم و كان آخر رقم مخزن في الكائن
                case "number":
                    switch (lastCharacter) {
                        case 'e':
                        case 'π':
                            textField.setText(textField.getText() + "×");
                            break;
                        case '0':
                            switch (textField.getText()) {
                                case "0":
                                case "+0":
                                case "-0":
                                case "×0":
                                case "÷0":
                                case "%0":
                                case "^0":
                                case "√0":
                                case "(0":
                                case "cos0":
                                case "sin0":
                                case "tan0":
                                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                                    break;
                            }
                            break;
                    }
                    break;

                // هو أحد العوامل التالية أو نقطة سيتم مسحه lastCharacter إذا تم النقر على عامل و كان الشيء المخزن في الكائن
                // بهذه الطريقة لن يستطيع المستخدم وضع أكثر من عاملين وراء بعض أو وضع عامل مباشرةً بعد نقطة
                case "operand":
                    switch (lastCharacter) {
                        case '+':
                        case '-':
                        case '×':
                        case '÷':
                        case '%':
                        case '.':
                            textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                            break;
                    }
                    break;

                // هو أحد الإحتمالات التالية lastCharacter إذا تم النقر على النقطة و كان الشيء المخزن في الكائن
                // سيتم إضافة 0 أو 0× بعدها, أو مسح النقطة لضمان عدم وجود نقطتين وراء بعض
                case "point":
                    switch (lastCharacter) {
                        case '+':
                        case '-':
                        case '×':
                        case '÷':
                        case '%':
                        case '(':
                        case '√':
                        case 'π':
                        case 's':
                        case 'n':
                        case '^':
                            textField.setText(textField.getText() + "0");
                            break;
                        case ')':
                            textField.setText(textField.getText() + "×0");
                            break;
                        case '.':
                            textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                            break;
                    }
                    break;

            }
        }
    }

    // double هذه الدالة ترجع ناتج المعادلة المدخلة في مربع النص كـ
    // فعلياً تقوم بتجزئة المعادلة على حسب العوامل و الرموز الموجودة فيها و كل جزئين تم إكتشاف قيمتهما يتحولان إلى جزء واحد
    // و هي تستمر على هذه الحال حتى تصبح كل الأجزاء تساوي رقماً واحداً, و عندها تقوم بإرجاعه
    private double calculate(String str) {

        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') {
                    nextChar();
                }
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm();
                    } else if (eat('-')) {
                        x -= parseTerm();
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('×')) {
                        x *= parseFactor();
                    } else if (eat('÷')) {
                        x /= parseFactor();
                    } else if (eat('%')) {
                        x %= parseFactor();
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')) {
                    return parseFactor();
                }
                if (eat('-')) {
                    return -parseFactor();
                }
                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if (eat('e')) {
                    x = Math.E;
                } else if (eat('π')) {
                    x = Math.PI;
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        nextChar();
                    }
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z' || ch == '√') {
                    while (ch >= 'a' && ch <= 'z' || ch == '√') {
                        nextChar();
                    }
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "√":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                if (eat('^')) {
                    x = Math.pow(x, parseFactor());
                }
                return x;

            }

        }.parse();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //   تم تجهيز جميع الدوال التالية للتأكد قبل إدخال أي حرف, رقم أو رمز
    //   ملاحظة سيتم إستدعاء هذه الدوال في حال قام المستخدم بالنقر على الأزرار الموجودة في النافذة أو من لوحة المفاتيح
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // عند النقر على الزر 0 سيتم إضافة 0 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b0_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "0");
    }

    // عند النقر على الزر 1 سيتم إضافة 1 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b1_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "1");
    }

    // عند النقر على الزر 2 سيتم إضافة 2 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b2_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "2");
    }

    // عند النقر على الزر 3 سيتم إضافة 3 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b3_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "3");
    }

    // عند النقر على الزر 4 سيتم إضافة 4 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b4_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "4");
    }

    // عند النقر على الزر 5 سيتم إضافة 5 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b5_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "5");
    }

    // عند النقر على الزر 6 سيتم إضافة 6 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b6_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "6");
    }

    // عند النقر على الزر 7 سيتم إضافة 7 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b7_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "7");
    }

    // عند النقر على الزر 8 سيتم إضافة 8 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b8_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "8");
    }

    // عند النقر على الزر 9 سيتم إضافة 9 في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void b9_isClicked() {
        autoAddOrRemove("number");
        textField.setText(textField.getText() + "9");
    }

    // في مربع النص مع إضافةالعامل × قبلها إذا دعت الحاجة sin سيتم إضافة الكلمة sin عند النقر على الزر
    private void sin_isClicked() {
        autoAddOrRemove("symbol");
        textField.setText(textField.getText() + "sin");
    }

    // في مربع النص مع إضافةالعامل × قبلها إذا دعت الحاجة cos سيتم إضافة الكلمة cos عند النقر على الزر
    private void cos_isClicked() {
        autoAddOrRemove("symbol");
        textField.setText(textField.getText() + "cos");
    }

    // في مربع النص مع إضافةالعامل × قبلها إذا دعت الحاجة tan سيتم إضافة الكلمة tan عند النقر على الزر
    private void tan_isClicked() {
        autoAddOrRemove("symbol");
        textField.setText(textField.getText() + "tan");
    }

    // عند النقر على الزر √ سيتم إضافة √ في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void sqrt_isClicked() {
        autoAddOrRemove("symbol");
        textField.setText(textField.getText() + "√");
    }

    // في مربع النص مع إضافةالعامل × قبله إذا دعت الحاجة e سيتم إضافة e عند النقر على الزر
    private void exponential_isClicked() {
        autoAddOrRemove("symbol");
        textField.setText(textField.getText() + "e");
    }

    // في مربع النص مع إضافةالعامل × قبله إذا دعت الحاجة π سيتم إضافة π عند النقر على الزر
    private void pi_isClicked() {
        autoAddOrRemove("symbol");
        textField.setText(textField.getText() + "π");
    }

    // عند النقر على الزر + سيتم إضافة + في مربع النص
    private void plus_isClicked() {
        autoAddOrRemove("operand");
        textField.setText(textField.getText() + "+");
    }

    // عند النقر على الزر - سيتم إضافة - في مربع النص
    private void minus_isClicked() {
        autoAddOrRemove("operand");
        textField.setText(textField.getText() + "-");
    }

    // عند النقر على الزر × سيتم إضافة × في مربع النص
    private void multiple_isClicked() {
        if (!textField.getText().isEmpty()) {
            autoAddOrRemove("operand");
            textField.setText(textField.getText() + "×");
        }
    }

    // عند النقر على الزر ÷ سيتم إضافة ÷ في مربع النص
    private void divide_isClicked() {
        if (!textField.getText().isEmpty()) {
            autoAddOrRemove("operand");
            textField.setText(textField.getText() + "÷");
        }
    }

    // عند النقر على الزر % سيتم إضافة % في مربع النص
    private void modulo_isClicked() {
        if (!textField.getText().isEmpty()) {
            autoAddOrRemove("operand");
            textField.setText(textField.getText() + "%");
        }
    }

    // π أو e, عند النقر على الزر ^ سيتم إضافة ^ في مربع النص في حال كان آخر حرف فيه عبارة عن رقم
    private void power_isClicked() {
        if (textField.getText().matches(".*[0-9eπ)]$")) {
            textField.setText(textField.getText() + "^");
        }
    }

    // n أو s ,√ ,( عند النقر على الزر ( سيتم إضافة ( في مربع النص في حال لم يكن مربع نص فارغاً و في حال لم يكن آخر حرف فيه عبارة عن
    private void parentesesRight_isClicked() {
        if (textField.getText().matches(".*[^ns√(]$")) {
            int leftParentesesCounter = 0, rightParentesesCounter = 0;
            for (char c : textField.getText().toCharArray()) {
                if (c == '(') {
                    leftParentesesCounter++;
                } else if (c == ')') {
                    rightParentesesCounter++;
                }
            }
            if (leftParentesesCounter > rightParentesesCounter) {
                textField.setText(textField.getText() + ")");
            }
        }
    }

    // عند النقر على الزر ) سيتم إضافة ) في مربع النص مع إضافة العامل × قبله إذا دعت الحاجة
    private void parentesesLeft_isClicked() {
        autoAddOrRemove("symbol");
        textField.setText(textField.getText() + "(");
    }

    // عند النقر على زر النقطة ( أي الفاصلة ) سيتم إضافة نقطة في مربع النص مع إضافة 0 قبلها في حال كان مربع النص فارغاً
    private void comma_isClicked() {
        String str = textField.getText();
        if (textField.getText().isEmpty()) {
            textField.setText("0.");
        } else {
            int lastPointIndex = str.lastIndexOf(".");
            int lastPlusIndex = str.lastIndexOf("+");
            int lastMinusIndex = str.lastIndexOf("-");
            int lastMultipleIndex = str.lastIndexOf("×");
            int lastDivideIndex = str.lastIndexOf("÷");
            int lastModuloIndex = str.lastIndexOf("%");

            if (lastPointIndex <= lastPlusIndex
                    || lastPointIndex <= lastMinusIndex
                    || lastPointIndex <= lastMultipleIndex
                    || lastPointIndex <= lastDivideIndex
                    || lastPointIndex <= lastModuloIndex) {
                autoAddOrRemove("point");
                textField.setText(textField.getText() + ".");
            }
        }
    }

    // لتحليل المعادلة التي أدخلها المستخدم ثم عرض الناتج النهائي calculate() عند النقر على زر المساواة = سيتم إستدعاء الدالة
    private void equal_isClicked() {
        if (!textField.getText().isEmpty()) {
            String historyNewText = historyText.getText() + textField.getText() + "\n= ";
            try {
                Double answer = calculate(textField.getText());
                if (answer.isInfinite()) {
                    textField.setText("cannot divide by 0");
                    historyNewText += "cannot divide by 0";
                } else if (answer.isNaN()) {
                    textField.setText("Error");
                    historyNewText += "Error";
                } else {
                    textField.setText(format.format(answer));
                    historyNewText += format.format(answer);
                }
            } catch (Exception ex) {
                textField.setText("Error");
                historyNewText += "Error";
            }
            historyText.setText(historyNewText + "\n\n");
        }
    }

    // عند النقر على الزر ← سيتم مسح رقم, رمز أو كلمة واحدة من مربع النص أو مسح أي خطأ ظاهر
    private void back_isClicked() {
        String temp = textField.getText();
        if (temp.equals("Error") || temp.equals("cannot divide by 0")) {
            textField.setText("");
        } else if (!temp.isEmpty()) {
            temp = textField.getText().substring(0, textField.getText().length() - 1);
            if (temp.length() >= 2) {
                switch (temp.substring(temp.length() - 2)) {
                    case "co":
                    case "si":
                    case "ta":
                        temp = temp.substring(0, temp.length() - 2);
                        break;
                }
            }
            textField.setText(temp);
        }
    }

    // سيتم مسح كل شيء ظاهر و تصفير جميع القيم المخزنة للبدء من جديد C عند النقر على الزر
    private void clear_isClicked() {
        textField.setText("");
    }

    @Override
    public void start(Stage stage) {

        // هنا قمنا بإضافة كل عنصر داخل القائمة المناسبة له بالترتيب الذي نريد أن يتم عرضهم فيه
        menuBar.getMenus().addAll(view, edit, help);
        view.getItems().add(history);
        edit.getItems().addAll(copy, paste, new SeparatorMenuItem(), copyHistory, clearHistory);
        help.getItems().addAll(keyboardShortcuts, about);

        // هنا قمنا بتحديد حجم و موقع كل شيء أضفناه في النافذة
        historyText.setPrefSize(256, 311);
        historyText.setTranslateX(260);
        historyText.setTranslateY(33);
        textField.setPrefSize(234, 60);
        textField.setTranslateX(11);
        textField.setTranslateY(33);
        cos.setPrefSize(45, 38);
        cos.setTranslateX(10);
        cos.setTranslateY(101);
        sin.setPrefSize(45, 38);
        sin.setTranslateX(58);
        sin.setTranslateY(101);
        tan.setPrefSize(45, 38);
        tan.setTranslateX(106);
        tan.setTranslateY(101);
        back.setPrefSize(45, 38);
        back.setTranslateX(154);
        back.setTranslateY(101);
        clear.setPrefSize(45, 38);
        clear.setTranslateX(202);
        clear.setTranslateY(101);
        pi.setPrefSize(45, 38);
        pi.setTranslateX(10);
        pi.setTranslateY(142);
        exponential.setPrefSize(45, 38);
        exponential.setTranslateX(58);
        exponential.setTranslateY(142);
        modulo.setPrefSize(45, 38);
        modulo.setTranslateX(106);
        modulo.setTranslateY(142);
        parentesesLeft.setPrefSize(45, 38);
        parentesesLeft.setTranslateX(154);
        parentesesLeft.setTranslateY(142);
        parentesesRight.setPrefSize(45, 38);
        parentesesRight.setTranslateX(202);
        parentesesRight.setTranslateY(142);
        b7.setPrefSize(45, 38);
        b7.setTranslateX(10);
        b7.setTranslateY(183);
        b8.setPrefSize(45, 38);
        b8.setTranslateX(58);
        b8.setTranslateY(183);
        b9.setPrefSize(45, 38);
        b9.setTranslateX(106);
        b9.setTranslateY(183);
        plus.setPrefSize(45, 38);
        plus.setTranslateX(154);
        plus.setTranslateY(183);
        power.setPrefSize(45, 38);
        power.setTranslateX(202);
        power.setTranslateY(183);
        b4.setPrefSize(45, 38);
        b4.setTranslateX(10);
        b4.setTranslateY(224);
        b5.setPrefSize(45, 38);
        b5.setTranslateX(58);
        b5.setTranslateY(224);
        b6.setPrefSize(45, 38);
        b6.setTranslateX(106);
        b6.setTranslateY(224);
        minus.setPrefSize(45, 38);
        minus.setTranslateX(154);
        minus.setTranslateY(224);
        sqrt.setPrefSize(45, 38);
        sqrt.setTranslateX(202);
        sqrt.setTranslateY(224);
        b1.setPrefSize(45, 38);
        b1.setTranslateX(10);
        b1.setTranslateY(265);
        b2.setPrefSize(45, 38);
        b2.setTranslateX(58);
        b2.setTranslateY(265);
        b3.setPrefSize(45, 38);
        b3.setTranslateX(106);
        b3.setTranslateY(265);
        multiple.setPrefSize(45, 38);
        multiple.setTranslateX(154);
        multiple.setTranslateY(265);
        equal.setPrefSize(45, 79);
        equal.setTranslateX(202);
        equal.setTranslateY(265);
        b0.setPrefSize(93, 38);
        b0.setTranslateX(10);
        b0.setTranslateY(306);
        comma.setPrefSize(45, 38);
        comma.setTranslateX(106);
        comma.setTranslateY(306);
        divide.setPrefSize(45, 38);
        divide.setTranslateX(154);
        divide.setTranslateY(306);
        menuBar.setPrefSize(5000, 20);

        // هنا قمنا بتحديد حجم و نوع خط النص الذي سيتم إدخاله في مربع النص بالإضافة إلى جعل النص يبدأ بالظهور من اليمين إلى اليسار
        textField.setAlignment(Pos.CENTER_RIGHT);

        // بعد أن نربطه فيه style.css لكل عنصر في النافذة حتى يتم تطبيق التصميم الذي وضعناه لهم في الملف Id هنا قمنا بإعطاء إسم
        b0.setId("b0");
        b1.setId("b1");
        b2.setId("b2");
        b3.setId("b3");
        b4.setId("b4");
        b5.setId("b5");
        b6.setId("b6");
        b7.setId("b7");
        b8.setId("b8");
        b9.setId("b9");
        comma.setId("comma");
        plus.setId("plus");
        minus.setId("minus");
        multiple.setId("multiple");
        divide.setId("divide");
        equal.setId("equal");
        cos.setId("cos");
        sin.setId("sin");
        tan.setId("tan");
        pi.setId("pi");
        exponential.setId("exponential");
        modulo.setId("modulo");
        parentesesLeft.setId("parentesesLeft");
        parentesesRight.setId("parentesesRight");
        sqrt.setId("sqrt");
        power.setId("power");
        clear.setId("clear");
        back.setId("back");
        textField.setId("textField");
        historyText.setId("historyText");
        menuBar.setId("menuBar");
        root.setId("root");

        // هنا قلنا أنه لن يتم البقاء فوق أي زر أو مربع نص بعد النقر عليه حتى يظل المستخدم قادراً على إستخدام لوحة المفاتيح
        // Enter فمثلاً لكي يتم طباعة جواب العملية الحسابية المدخلة في حال قام المستخدم بالنقر على الزر
        textField.setFocusTraversable(false);
        textField.setEditable(false);
        b0.setFocusTraversable(false);
        b1.setFocusTraversable(false);
        b2.setFocusTraversable(false);
        b3.setFocusTraversable(false);
        b4.setFocusTraversable(false);
        b5.setFocusTraversable(false);
        b6.setFocusTraversable(false);
        b7.setFocusTraversable(false);
        b8.setFocusTraversable(false);
        b9.setFocusTraversable(false);
        equal.setFocusTraversable(false);
        clear.setFocusTraversable(false);
        back.setFocusTraversable(false);
        sin.setFocusTraversable(false);
        cos.setFocusTraversable(false);
        tan.setFocusTraversable(false);
        pi.setFocusTraversable(false);
        exponential.setFocusTraversable(false);
        modulo.setFocusTraversable(false);
        parentesesLeft.setFocusTraversable(false);
        parentesesRight.setFocusTraversable(false);
        sqrt.setFocusTraversable(false);
        power.setFocusTraversable(false);
        multiple.setFocusTraversable(false);
        plus.setFocusTraversable(false);
        minus.setFocusTraversable(false);
        comma.setFocusTraversable(false);
        historyText.setFocusTraversable(false);
        historyText.setEditable(false);

        // يمثل ما يحدث عند النقر على أي زر موضوع في البرنامج EventHandler هنا قمنا بتعريف كائن من الإنترفيس
        EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
            actionPerformed(e);
        };

        // eventHandler هنا قمنا بربط جميع الأزرار التي وضعناه في النافذة و في شريط القوائم بالكائن
        // e عند النقر على أي زر موجود في النافذة مع تمرير الكائن الذي يمثل الشيء تم النقر عليه مكان المتغير actionPerformed() حتى يتم إستدعاء الدالة
        history.addEventHandler(ActionEvent.ACTION, eventHandler);
        copy.addEventHandler(ActionEvent.ACTION, eventHandler);
        paste.addEventHandler(ActionEvent.ACTION, eventHandler);
        copyHistory.addEventHandler(ActionEvent.ACTION, eventHandler);
        clearHistory.addEventHandler(ActionEvent.ACTION, eventHandler);
        keyboardShortcuts.addEventHandler(ActionEvent.ACTION, eventHandler);
        about.addEventHandler(ActionEvent.ACTION, eventHandler);
        b0.addEventHandler(ActionEvent.ACTION, eventHandler);
        b1.addEventHandler(ActionEvent.ACTION, eventHandler);
        b2.addEventHandler(ActionEvent.ACTION, eventHandler);
        b3.addEventHandler(ActionEvent.ACTION, eventHandler);
        b4.addEventHandler(ActionEvent.ACTION, eventHandler);
        b5.addEventHandler(ActionEvent.ACTION, eventHandler);
        b6.addEventHandler(ActionEvent.ACTION, eventHandler);
        b7.addEventHandler(ActionEvent.ACTION, eventHandler);
        b8.addEventHandler(ActionEvent.ACTION, eventHandler);
        b9.addEventHandler(ActionEvent.ACTION, eventHandler);
        comma.addEventHandler(ActionEvent.ACTION, eventHandler);
        exponential.addEventHandler(ActionEvent.ACTION, eventHandler);
        pi.addEventHandler(ActionEvent.ACTION, eventHandler);
        cos.addEventHandler(ActionEvent.ACTION, eventHandler);
        sin.addEventHandler(ActionEvent.ACTION, eventHandler);
        tan.addEventHandler(ActionEvent.ACTION, eventHandler);
        sqrt.addEventHandler(ActionEvent.ACTION, eventHandler);
        power.addEventHandler(ActionEvent.ACTION, eventHandler);
        modulo.addEventHandler(ActionEvent.ACTION, eventHandler);
        parentesesLeft.addEventHandler(ActionEvent.ACTION, eventHandler);
        parentesesRight.addEventHandler(ActionEvent.ACTION, eventHandler);
        plus.addEventHandler(ActionEvent.ACTION, eventHandler);
        multiple.addEventHandler(ActionEvent.ACTION, eventHandler);
        divide.addEventHandler(ActionEvent.ACTION, eventHandler);
        minus.addEventHandler(ActionEvent.ACTION, eventHandler);
        equal.addEventHandler(ActionEvent.ACTION, eventHandler);
        clear.addEventHandler(ActionEvent.ACTION, eventHandler);
        back.addEventHandler(ActionEvent.ACTION, eventHandler);

        // root هنا أضفنا شريط القوائم, و مربعات النصوص و جميع الأزرار التي قمنا بتعريفها و تحديد خصائصها في الحاوية الأساسية في النافذة و التي إسمها
        root.getChildren().add(menuBar);
        root.getChildren().add(b0);
        root.getChildren().add(b1);
        root.getChildren().add(b2);
        root.getChildren().add(b3);
        root.getChildren().add(b4);
        root.getChildren().add(b5);
        root.getChildren().add(b6);
        root.getChildren().add(b7);
        root.getChildren().add(b8);
        root.getChildren().add(b9);
        root.getChildren().add(comma);
        root.getChildren().add(equal);
        root.getChildren().add(plus);
        root.getChildren().add(multiple);
        root.getChildren().add(minus);
        root.getChildren().add(divide);
        root.getChildren().add(cos);
        root.getChildren().add(sin);
        root.getChildren().add(tan);
        root.getChildren().add(sqrt);
        root.getChildren().add(power);
        root.getChildren().add(modulo);
        root.getChildren().add(exponential);
        root.getChildren().add(pi);
        root.getChildren().add(parentesesRight);
        root.getChildren().add(parentesesLeft);
        root.getChildren().add(clear);
        root.getChildren().add(back);
        root.getChildren().add(textField);
        root.getChildren().add(historyText);

        // فيها و تحديد حجمها Node كأول root هنا قمنا بإنشاء محتوى النافذة مع تعيين الكائن
        Scene scene = new Scene(root, 247, 343);

        // scene بالكائن css الذي وضعناه في المجلد style.css هنا قمنا بربط الملف
        // scene هكذا سيتم تطبيق كود التصميم الموضوع فيه على الأشياء الموضوعة في الكائن
        scene.getStylesheets().add("css/style.css");

        // كالكائن الذي سيمثل نافذة التطبيق myStage هنا قمنا بوضع الكائن
        myStage = stage;
        // هنا وضعنا عنوان للنافذة
        myStage.setTitle("Scientific Calculator");
        // أي وضعنا محتوى النافذة الذي قمنا بإنشائه للنافذة .stage في كائن الـ scene هنا وضعنا كائن الـ
        myStage.setScene(scene);
        // هنا قمنا بإظهار النافذة
        myStage.show();
        // هنا جعلنا المستخدم غير قادر على تكبير و تصغير حجم الشاشة بشكل يدوي
        myStage.setResizable(false);

        // عند الضغط على أي حرف من لوحة المفاتيح سيتم تنفيذ الأوامر الموضوعة هنا
        // e سيتم تمرير يمرر الحرف الذي تم النقر عليه مكان الكائن
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {

            if (null != e.getCode()) {
                // لاحظ أنه على أساس الحرف المدخل سيتم إستدعاء الدالة المناسبة و التي بدورها ستكتبه في مربع النص
                switch (e.getCode())
                {
                    case NUMPAD0:         // هذا الثابت يمثل الرقم 0
                        b0_isClicked();
                        break;

                    case NUMPAD1:         // هذا الثابت يمثل الرقم 1
                        b1_isClicked();
                        break;

                    case NUMPAD2:         // هذا الثابت يمثل الرقم 2
                        b2_isClicked();
                        break;

                    case NUMPAD3:         // هذا الثابت يمثل الرقم 3
                        b3_isClicked();
                        break;

                    case NUMPAD4:         // هذا الثابت يمثل الرقم 4
                        b4_isClicked();
                        break;

                    case NUMPAD5:         // هذا الثابت يمثل الرقم 5
                        b5_isClicked();
                        break;

                    case NUMPAD6:         // هذا الثابت يمثل الرقم 6
                        b6_isClicked();
                        break;

                    case NUMPAD7:         // هذا الثابت يمثل الرقم 7
                        b7_isClicked();
                        break;

                    case NUMPAD8:         // هذا الثابت يمثل الرقم 8
                        b8_isClicked();
                        break;

                    case NUMPAD9:         // هذا الثابت يمثل الرقم 9
                        b9_isClicked();
                        break;

                    case S:               // s هذا الثابت يمثل الحرف
                        sin_isClicked();
                        break;

                    case C:               // c هذا الثابت يمثل الحرف
                        cos_isClicked();
                        break;

                    case T:               // t هذا الثابت يمثل الحرف
                        tan_isClicked();
                        break;

                    case V:               // v هذا الثابت يمثل الحرف
                        sqrt_isClicked();
                        break;

                    case E:               // e هذا الثابت يمثل الحرف
                        exponential_isClicked();
                        break;

                    case P:               // p هذا الثابت يمثل الحرف
                        pi_isClicked();
                        break;

                    // Shift ملاحظة: الأحرف التالية لا تطلب النقر على الزر

                    case PLUS:            // + هذا الثابت يمثل الرمز
                        plus_isClicked();
                        break;

                    case MINUS:           // - هذا الثابت يمثل الرمز
                        minus_isClicked();
                        break;

                    case MULTIPLY:        // * هذا الثابت يمثل الرمز
                        multiple_isClicked();
                        break;

                    case DIVIDE:          // / هذا الثابت يمثل الرمز
                        divide_isClicked();
                        break;

                    case DIGIT5:          // % هذا الثابت يمثل الرمز
                        modulo_isClicked();
                        break;

                    case DIGIT6:          // ^ هذا الثابت يمثل الرمز
                        power_isClicked();
                        break;

                    case DIGIT0:          // ) هذا الثابت يمثل الرمز
                        parentesesRight_isClicked();
                        break;

                    case DIGIT9:          // ( هذا الثابت يمثل الرمز
                        parentesesLeft_isClicked();
                        break;

                    case COMMA:           // . هذا الثابت يمثل الرمز
                        comma_isClicked();
                        break;

                    case ENTER:           // Enter هذا الثابت يمثل الزر
                        equal_isClicked();
                        break;

                    case BACK_SPACE:      // BackSpace هذا الثابت يمثل الزر
                        back_isClicked();
                        break;

                    case DELETE:          // Del هذا الثابت يمثل الزر
                        clear_isClicked();
                        break;
                }
            }
        });
    }

    // هنا ربطنا الدوال التي قمنا بتعريفها سابقاً بجميع الأزرار الموجودة في النافذة
    // بكل بساطة, الزر الذي يتم النقر عليه سيقوم بتنفيذ الدالة التي صممت لأجله
    // في الأخير قمنا بتعريف ما يحدث عند النقر على أي زر موجود في شريط القوائم
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b0)
            b0_isClicked();

        else if (e.getSource() == b1)
           b1_isClicked();

        else if (e.getSource() == b2)
           b2_isClicked();

        else if (e.getSource() == b3)
           b3_isClicked();

        else if (e.getSource() == b4)
           b4_isClicked();

        else if (e.getSource() == b5)
           b5_isClicked();

        else if (e.getSource() == b6)
           b6_isClicked();

        else if (e.getSource() == b7)
           b7_isClicked();

        else if (e.getSource() == b8)
           b8_isClicked();

        else if (e.getSource() == b9)
           b9_isClicked();

        else if (e.getSource() == sin)
           sin_isClicked();

        else if (e.getSource() == cos)
           cos_isClicked();

        else if (e.getSource() == tan)
           tan_isClicked();

        else if (e.getSource() == sqrt)
           sqrt_isClicked();

        else if (e.getSource() == exponential)
           exponential_isClicked();

        else if (e.getSource() == pi)
           pi_isClicked();

        else if (e.getSource() == plus)
           plus_isClicked();

        else if (e.getSource() == minus)
           minus_isClicked();

        else if (e.getSource() == multiple)
           multiple_isClicked();

        else if (e.getSource() == divide)
           divide_isClicked();

        else if (e.getSource() == modulo)
           modulo_isClicked();

        else if (e.getSource() == power)
           power_isClicked();

        else if (e.getSource() == parentesesRight)
           parentesesRight_isClicked();

        else if (e.getSource() == parentesesLeft)
           parentesesLeft_isClicked();

        else if (e.getSource() == comma)
           comma_isClicked();

        else if (e.getSource() == equal)
           equal_isClicked();

        else if (e.getSource() == back)
           back_isClicked();

        else if (e.getSource() == clear)
           clear_isClicked();

        // من شريط القوائم سيتم تكبير حجم النافذة لإظهاره history إذا تم تفعيل الـ
        else if (e.getSource() == history) {
            if (history.isSelected())
                myStage.setWidth(532);
            else
                myStage.setWidth(263);
        }

        // الموجود في شريط القوائم سيتم نسخ المعادلة المدخلة في مربع النص copy عند النقر على الزر
        else if (e.getSource() == copy) {
            textField.selectAll();
            textField.copy();
            textField.positionCaret(textField.getText().length());
        }

        // الموجود في شريط القوائم سيتم لصق أي نص تم نسخه سابقاً في آخر مربع النص paste عند النقر على الزر
        else if (e.getSource() == paste) {
            textField.paste();
        }

        // الموجود في شريط القوائم سيتم نسخ جميع المعادلات و الإجابات المخزنة سابقاً copyHistory عند النقر على الزر
        else if (e.getSource() == copyHistory) {
            historyText.selectAll();
            historyText.copy();
            textField.positionCaret(textField.getText().length());
        }

        // الموجود في شريط القوائم سيتم مسح جميع المعادلات و الإجابات المخزنة سابقاً clearHistory عند النقر على الزر
        else if (e.getSource() == clearHistory) {
            historyText.setText("");
        }

        // الموجود في شريط القوائم سيتم إظهار نافذة منبثقة تحتوي على إختصارات لوحة المفاتيح keyboardShortcuts عند النقر على الزر
        else if (e.getSource() == keyboardShortcuts) {
            String str
                    = "Press  V  to add √.\n"
                    + "Press  P  to add π.\n"
                    + "Press  C  to add cos.\n"
                    + "Press  S  to add sin.\n"
                    + "Press  T  to add tan.\n"
                    + "Press  Enter  to get the result\n"
                    + "Press  BackSpace  to clear last character entered.\n"
                    + "Press  Delete  to clear all characters entered.";

            // و من ثم قمنا بإظهارها alert هنا قمنا بتمرير النص الذي نريد وضعه في النافذة المنبثقة
            alert.setTitle("Keyboard Shortcuts");
            alert.setHeaderText("Keyboard Shortcuts");
            alert.setContentText(str);
            alert.showAndWait();
        }

        // الموجود في شريط القوائم سيتم إظهار نافذة منبثقة فيها معلومات حول البرنامج about عند النقر على الزر
        else if (e.getSource() == about) {
            String str
                    = "Prepared by Mhamad Harmush\n\n"
                    + "If you have any comments, ideas.. just let me know\n\n"
                    + "Email:   mhamad.harmush@gmail.com\n"
                    + "Twitter & Facebook:   @MhamadHarmush\n\n"
                    + "Note\n"
                    + "I used JDK 1.8 to compile the source code.\n\n"
                    + "© Copyright 2019 harmash.com - All Rights Reserved";

            // و من ثم قمنا بإظهارها alert هنا قمنا بتمرير النص الذي نريد وضعه في النافذة المنبثقة
            alert.setTitle("About");
            alert.setHeaderText("About");
            alert.setContentText(str);
            alert.showAndWait();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
