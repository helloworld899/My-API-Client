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
            System.out.println("|  Welcome! What would you like to do?  |");
            System.out.println("=========================================");
            System.out.println("| Pick by number:                       |");
            System.out.println("|     [1] Get list of blogs             |");
            System.out.println("|     [2] Add a blog to the list        |");
            System.out.println("|     [3] Clear list of blogs           |");
            System.out.println("|     [4] Delete a blog                 |");
            System.out.println("|     [5] Update a blog                 |");
            System.out.println("|     [6] Search for a blog             |");
            System.out.println("|     [7] Exit program                  |");
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
                    searchBlog();
                    break;
                case 7:
                    System.out.println("Goodbye!!");
                    runProgram = false;

            }
        }
    }

    public void printListOfBlogs() {
        Blog[] blogs = myApiClient.listBlogs();

        System.out.println("All written blog posts");
        System.out.println("------------------------------------------");

        if (blogs.length > 0) {
            for (int i = 0; i < blogs.length; i++) {
                int id = blogs[i].id;
                String title = blogs[i].title;
                String text = blogs[i].text;
                String date = blogs[i].date;

                System.out.println("Blog post id: " + "#" + id);
                System.out.printf("Title: %s \n", title + "\n" + "Content: " + text + "\n");
                System.out.println("                         Posted on: " + date);
                System.out.println("-------------------------------------------");

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
        Blog blog = myApiClient.getBlogByID(getID);

        if (blog != null) {

            int blogID = blog.getId();

            myApiClient.deleteSpecificBlogByID(blog);

            System.out.println("Blog ID: " + blogID + " has now been deleted!");


        } else {
            System.out.println("No blog with this ID or has been deleted");
        }
    }


    public void updateBlog() {
        System.out.println("Type in the blog ID you want to Update");
        int getID = getUserInt();
        Blog blog = myApiClient.getBlogByID(getID);


        if (blog != null) {

            System.out.println("What do you wanna change?");
            System.out.println("1. Title");
            System.out.println("2. Content");
            System.out.println("3. Date");
            int userInput = getUserInt();

            if (userInput == 1) {
                System.out.println("Update the Title");
                String blogTitle = getUserString();
                blog.setTitle(blogTitle);
            }
            if (userInput == 2) {
                System.out.println("Content:");
                String blogText = getUserString();
                blog.setText(blogText);
            }
            if (userInput == 3) {
                System.out.println("Date");
                String blogDate = getUserString();
                blog.setDate(blogDate);
            }

            int blogID = blog.getId();

            myApiClient.updateSpecificBlogByID(getID, blog);

            System.out.println("Blog ID: " + blogID + " updated!");

        } else {
            System.out.println("Issue updating Blog. :(");
        }
    }


    public void searchBlog() {

        System.out.println("Type in the blog ID you want to find");
        int getID = getUserInt();

        Blog blog = myApiClient.getBlogByID(getID);

        System.out.println("Blogs by ID");
        System.out.println("---------------------------------");


        if (blog != null) {

            int blogID = blog.getId();
            String blogTitle = blog.getTitle();
            String blogText = blog.getText();
            String blogDate = blog.getDate();

            System.out.println("Blog ID: " + blogID);
            System.out.println("\n Title: " + blogTitle);
            System.out.println(blogText);
            System.out.println("       Posted on: " + blogDate);


            System.out.println("\n Here is the blog you wanted");
        } else {
            System.out.println("Issue finding the blog you wanted");
        }
    }

    public String getUserString() {
        Scanner myScanner = new Scanner(System.in);

        String myString;

        while (true) {
            try {
                System.out.print("> ");
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




