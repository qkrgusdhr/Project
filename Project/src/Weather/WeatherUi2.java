package Weather;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherUi2 extends Frame {

    private TextArea textArea;
    private Choice choice;
    private JPanel imagePanel; // 이미지를 표시할 패널
    private JLabel weatherImageLabel; // 날씨 이미지를 표시할 레이블

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WeatherUi2 window = new WeatherUi2();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public WeatherUi2() {
        initialize();
        // 기본 이미지 설정
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("def.jpg"));
        Image defaultImg = defaultIcon.getImage();
        Image scaledDefaultImg = defaultImg.getScaledInstance(440, 300, Image.SCALE_SMOOTH); // 기본 이미지 크기 설정
        defaultIcon = new ImageIcon(scaledDefaultImg);
        weatherImageLabel.setIcon(defaultIcon);
    }
    
    private void initialize() {
        setTitle("Weather Info");
        setSize(450, 450); // 프레임 전체 크기 조정
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Panel panel = new Panel();
        add(panel, BorderLayout.NORTH);

        choice = new Choice();
        choice.add("서울특별시");
        choice.add("부산광역시");
        choice.add("대구광역시");
        choice.add("인천광역시");
        choice.add("광주광역시");
        choice.add("대전광역시");
        choice.add("울산광역시");
        choice.add("세종특별자치시");
        panel.add(choice);

        Button button = new Button("날씨 조회");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getWeatherInfo();
            }
        });
        panel.add(button);

        // 이미지를 표시할 패널 생성
        imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        add(imagePanel, BorderLayout.CENTER); // 이미지 패널을 중앙에 배치

        // 날씨 이미지를 표시할 레이블 생성
        weatherImageLabel = new JLabel();
        imagePanel.add(weatherImageLabel);

        // 텍스트 영역 크기 조정
        textArea = new TextArea();
        textArea.setBackground(Color.WHITE);
        textArea.setEditable(false);
        add(textArea, BorderLayout.SOUTH); // 텍스트 출력 영역을 아래에 배치
        textArea.setPreferredSize(new Dimension(450, 100)); // 텍스트 영역의 크기를 조정
    }

    private void getWeatherInfo() {
        try {
            String[] locations = { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시" };
            String[] lats = { "37.566386", "35.1795543", "35.8714354", "37.4558839", "35.1595454", "36.350411",
                    "35.539651", "36.480132" };
            String[] lons = { "126.977948", "129.0756416", "128.601445", "126.705206", "126.852601", "127.3845475",
                    "129.311358", "127.289021" };

            int index = choice.getSelectedIndex();
            String lat = lats[index];
            String lon = lons[index];
            String location = locations[index];

            String urlstr = "http://api.openweathermap.org/data/2.5/weather?" + "lat=" + lat + "&lon=" + lon
                    + "&appid=14a9ab8be0d52aba1f5d2318e5dc9088";
            URL url = new URL(urlstr);
            BufferedReader bf;
            String line;
            StringBuilder result = new StringBuilder();

            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = bf.readLine()) != null) {
                result.append(line);
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result.toString());

            textArea.setText("지역 : " + location + "\n");

            JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
            JSONObject obj = (JSONObject) weatherArray.get(0);
            String weatherMain = (String) obj.get("main");
            String weatherDescription = (String) obj.get("description");

            String weatherString = "";
            
            switch (weatherMain) {
                case "Clouds":
                    weatherString = "흐림";
                    textArea.append(
                            "날씨 : " + weatherString + "\n" + "(" + "비가 올 수도 있으니 [DB-DogName]와 산책시 우산을 챙기세요." + ")\n");
                    break;
                case "Clear":
                    weatherString = "맑음";
                    textArea.append(
                            "날씨 : " + weatherString + "\n" + "(" + "화창한 날씨입니다. [DB-DogName]와 산책하기 좋은 날입니다!" + ")\n");
                    break;
                case "Snow":
                    weatherString = "눈";
                    textArea.append(
                            "날씨 : " + weatherString + "\n" + "(" + "눈이 내립니다. 외출시 [DB-DogName]에게 따뜻한 옷을 입혀주세요!" + ")\n");
                    break;
                case "Rain":
                    weatherString = "비";
                    textArea.append(
                            "날씨 : " + weatherString + "\n" + "(" + "비가 내립니다. [DB-DogName]에겐 미안하지만 산책은 힘들겠네요." + ")\n");
                    break;
                default:
                    break;
            }

            textArea.append("\n");

            JSONObject mainArray = (JSONObject) jsonObj.get("main");
            double ktemp = Double.parseDouble(mainArray.get("temp").toString());
            double temp = ktemp - 273.15;
            textArea.append(String.format("온도 : %.2f\n", temp));

            // 이미지 로딩 및 표시
            ImageIcon icon = null;
            
            switch (weatherMain) {
                case "Clouds":
                    icon = new ImageIcon(getClass().getResource("clouds.jpg"));
                    break;
                case "Clear":
                    icon = new ImageIcon(getClass().getResource("clear.jpg"));
                    break;
                case "Snow":
                    icon = new ImageIcon(getClass().getResource("snow.jpg"));
                    break;
                case "Rain":
                    icon = new ImageIcon(getClass().getResource("rain.jpg"));
                    break;
                default:
                	icon = new ImageIcon(getClass().getResource("def.jpg"));
                    break;
            }
         // 이미지 아이콘의 크기를 조절하여 패널에 맞게 설정
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImg);

            weatherImageLabel.setIcon(icon); // 이미지를 레이블에 설정

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
