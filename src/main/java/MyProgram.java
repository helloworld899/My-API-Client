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
            System.out.println("Hello! What would you like to do?");
            System.out.println("1. Get list of blogs");
            System.out.println("2. Add a blog to the list");
            System.out.println("3. Clear list of blogs");
            System.out.println("4. Delete a blog");
            System.out.println("5. Update a blog");
            System.out.println("6. Exit program");
            System.out.println("=========================================\n");
            System.out.println();

            int userInput = getUserInt();

            System.out.println("User picked: " + userInput);

            switch (userInput) {
                case 1:
                    printListOfBlogs();
                    break;
                case 2:
                    addBlog();
                    break;
                case 3:
                    clearListOfBlogs();
                    break;
                case 4:
                    deleteBlog();
                    break;
                case 5:
                    updateBlog();
                    break;
                case 6:
                    System.out.println("Goodbye!!");
                    runProgram = false;

            }
        }
    }

    public void printListOfBlogs() {
        Blog[] blogs = myApiClient.listBlogs();

        System.out.println("All written blog posts");
        System.out.println("--------------------------------------");

        if (blogs.length > 0) {
            for (int i = 0; i < blogs.length; i++) {
                int id = blogs[i].id;
                String title = blogs[i].title;
                String text = blogs[i].text;
                String date = blogs[i].date;

                System.out.println("Title: " + title);
                System.out.printf("Blog post id: %s \n", id + "\n" + text + "\n");
                System.out.println("                         Posted on: " + date);
                System.out.println("-------------------------------------------");
                System.out.println("");
            }
        } else {
            System.out.println("No blogs in the list... ");
        }
    }


    public void addBlog() {
        System.out.println("Title of your blog");
        String title = getUserString();

        System.out.println("Write something about your day");
        String text = getUserString();

        System.out.println("Type in today's date in format yy/mm/dd");
        String date = getUserString();

        Blog newBlog = new Blog(title, text, date);

        boolean success = myApiClient.addBlog(newBlog);

        if (success) {
            System.out.println("New blog entry added!");
        } else {
            System.out.println("Issues adding a blog, try again :(");
        }
    }

    public void clearListOfBlogs() {
        if (myApiClient.clearAllBlogs()) {
            System.out.println("List of blogs cleared!");
        } else {
            System.out.println("Issue clearing list of blogs. :(");
        }
    }


    public void deleteBlog() {
        System.out.println("Type in Blog ID to remove");
        int getID = getUserInt();
        Blog[] blog = myApiClient.listBlogs();
        int i;
        if (blog.length > 0) {
            for (i = 0; i < blog.length; i++) {
                if (getID == blog[i].id) {
                    break;
                }
            }
            boolean success = myApiClient.deleteSpecificBlogByID(blog[i]);
            if (success) {
                System.out.println("This blog id nummer" + getID + ", is now deleted");
            }
        } else {
            System.out.println("Issue deleting this");
        }
    }


    public void updateBlog() {
        Blog[] blog = myApiClient.listBlogs();
        System.out.println("Type in the blog ID you want to Update");
        int getID = getUserInt();

        if (blog.length > 0) {
            for (int i = 0; i < blog.length; i++) {
                if (getID == blog[i].id) {
                    System.out.println("Update the Title?");
                    String title = getUserString();

                    System.out.println("Content:");
                    String text = getUserString();

                    System.out.println("Date");
                    String date = getUserString();

                    Blog newBlog = new Blog(getID, title, text, date);
                    boolean success = myApiClient.updateSpecificBlogByID(newBlog);
                    if (success) {
                        System.out.println("Blog updated!");
                    } else {
                        System.out.println("Issue updating Blog. :(");
                    }
                }
            }
        }
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

