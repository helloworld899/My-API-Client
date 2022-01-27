import java.util.Scanner;

public class MyProgram {
    private ApiClient myApiClient;

    public MyProgram() {
        myApiClient = new ApiClient("http://127.0.0.1:8080/api/v1");
        }

    public void start() {
        boolean runProgram = true;


        while (runProgram) {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("Hello hacker. What would you like to do?");
            System.out.println("1. Get list of blogs");
            System.out.println("2. Clear list of blogs");
            System.out.println("3. Add a blog to the list");
            System.out.println("4. Exit program");
            System.out.println("=========================================");
            System.out.println();

            int userInput = getUserInt();

            System.out.println("User picked: " + userInput);

            switch (userInput) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;







            }


        }



        }

    public void printListOfBlogs() {
        Blog[] blogs = myApiClient.getBlogs();

        System.out.println("Blogs");
        System.out.println("---------------------------------");

        if (blogs.length > 0) {
            for (int i = 0; i < blogs.length; i++) {
                String text = blogs[i].text;

                System.out.println("-> %s (%d/10)\n" + text);
            }
        } else {
            System.out.println("No blogs in the list: ");
        }
    }

    public void addBlog() {
        System.out.println("Write something about your day");
        String text = getUserString();

        Blog newBlog = new Blog(text);
    }






    public String getUserString() {
        Scanner myScanner = new Scanner(System.in);

        String myString;

        while (true) {
            try {
                System.out.println("> ");
                myString = myScanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        }
        return myString;
    }

    public int getUserInt() {
        Scanner myScanner = new Scanner(System.in);

        int myInteger;

        while (true) {
            try {
                System.out.print("> ");
                myInteger = Integer.parseInt(myScanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        }
        return myInteger;
    }
}
