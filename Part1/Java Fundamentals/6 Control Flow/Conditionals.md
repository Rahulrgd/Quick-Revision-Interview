In Java, **conditional statements** allow you to execute different blocks of code based on specific conditions. The most commonly used conditional statements are `if`, `else`, and `switch`. Here’s an overview of each, along with examples.

### 1. **If Statement**

The `if` statement evaluates a condition and executes the associated block of code if the condition is true.

```java
int number = 10;

if (number > 0) {
    System.out.println("The number is positive."); // This line will execute
}
```

### 2. **If-Else Statement**

The `if-else` statement allows you to specify an alternative block of code to execute when the condition is false.

```java
int number = -5;

if (number > 0) {
    System.out.println("The number is positive.");
} else {
    System.out.println("The number is not positive."); // This line will execute
}
```

### 3. **If-Else If-Else Statement**

You can chain multiple conditions using `if`, `else if`, and `else` statements.

```java
int score = 75;

if (score >= 90) {
    System.out.println("Grade: A");
} else if (score >= 80) {
    System.out.println("Grade: B");
} else if (score >= 70) {
    System.out.println("Grade: C"); // This line will execute
} else {
    System.out.println("Grade: F");
}
```

### 4. **Switch Statement**

The `switch` statement is used when you want to execute one block of code among multiple choices based on the value of a variable or expression. It's particularly useful for checking a variable against several constant values.

```java
int day = 3;
String dayName;

switch (day) {
    case 1:
        dayName = "Monday";
        break;
    case 2:
        dayName = "Tuesday";
        break;
    case 3:
        dayName = "Wednesday"; // This line will execute
        break;
    case 4:
        dayName = "Thursday";
        break;
    case 5:
        dayName = "Friday";
        break;
    case 6:
        dayName = "Saturday";
        break;
    case 7:
        dayName = "Sunday";
        break;
    default:
        dayName = "Invalid day"; // Executed if no case matches
}

System.out.println("Day " + day + " is " + dayName); // Output: Day 3 is Wednesday
```

### Example Program Using Conditionals

Here’s a complete Java program demonstrating the use of `if`, `else`, and `switch` statements:

```java
public class Main {
    public static void main(String[] args) {
        int number = 15;

        // If-Else Example
        if (number % 2 == 0) {
            System.out.println("The number is even.");
        } else {
            System.out.println("The number is odd."); // This line will execute
        }

        // Switch Example
        int month = 5;
        String monthName;

        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May"; // This line will execute
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
            default:
                monthName = "Invalid month"; // Executed if no case matches
        }

        System.out.println("Month " + month + " is " + monthName); // Output: Month 5 is May
    }
}
```

---

Conditional statements are essential for controlling the flow of a program, allowing it to make decisions and respond to different inputs dynamically. They form the backbone of logic in many applications, enabling complex behavior based on user input, data states, and other conditions.