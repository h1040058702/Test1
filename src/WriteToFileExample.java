import java.io.FileWriter;
import java.io.IOException;

class WriteToFileExample{
    public static void main(String[] args) {
        String content = "Hello,this is a test.\nJava FileWriter example.\nHave a great day!";

        String filepath = "output.txt";

        try(FileWriter writer = new FileWriter(filepath)){
            writer.write(content);

            System.out.println("内容已成功写入到" + filepath);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

