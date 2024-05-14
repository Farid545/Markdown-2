# Markdown Console Application
## 1. Overview:
<details>
    <summary>Click here to see the overview</summary>

### Description
This is a simple console application for converting our (custom) markup into HTML markup or ANSI. This application expects to receive a file with markup and converts it into selected markup using format flag, then outputs the result to the console.

### Capability
This project has good potential for further development, at least for educational purposes. It's possible to: 
1. Implement a web server with a nice UI
    - Implement a user friendly interface to avoid file path
    - Avoid console output (It's not a good practice as is not a user friendly)
2. Optimize the code
   - Refactoring
   - Better methods names
3. Better logic implementation.
    - Better converter logic implementation
    - Additional Unit testing
</details>

## 2. Steps to execute script:
<details>
   <summary>Click here to see the steps to run the app</summary>

To avoid building the app I decide to build It and add the JAR file to the git. I did It as to compile the java project we should java a **JDK (Java Dev Kit)**.<br>
However, to run the app we should use only **JRE (Java Runtime Engine)** <br>
<h3>To run this application, you should:</h3>
   - Install the JRE as java apps needs a JRE to run any app
   - Open the terminal in the project directory
   - Create a .txt file with input data
   - To run the app you should execute the following cmd: <br>
  ``
     java -ea -cp target/classes org.example.Main
  ``
   - After that you should input a file path. The file format - ***.txt!***
   - ea stands for enable assertions. It's essential for our test cases because otherwise they will never fail
</details>

## 3. Instruction:
<details>
   <summary>Click here to see the instruction</summary>

So, after execution the cmd you will see the text to enter file path.<br>
If you will enter a wrong path or wrong file format, the app will give you one more chance : )

If you entered a right file path, It has 2 cases:
1. If file have everything correct - It will output a correctly converted app to the console
2. If we have some format errors or something else, we will get the error code and the app will be finished with non-zero code (1)

### Important!
***The file format should be .txt!***

</details>

## 4. Revert-commits:
I've done two revert-commits.<br>
The first one I deleted a wrong method and pushed It to the git by chance.<br>
***You can find the first one revert-commit [here](https://github.com/Farid545/Markdown-2/commit/2bd526d55a888251b4894cac66b6e6b72d0102ce))!***


## 5. Unit tests:
In order to run unit tests correctly we should enable assertions as an argument during program compilation (```-ea```)
In summary there are 9 unit tests each covering specific case scenario, to execute them we should use command ```java -ea -cp target/classes org.example.test.TestFileProceed```
or using IDE (Intellij for example)
All test cases will be executed as a regular program, with throwing java.lang.AssertionError in failed case scenarios

## 6. GitHub actions: 
I've also prepared automatic test executor for test cases after each successful push. The GitHub actions will tell us whether 
everything is correct or not.
The GitHub actions is using command similar to one we used for execution of unit tests: ```java -ea -cp target/classes org.example.test.TestFileProceed```

***You can find successful test execution via GitHub [here](https://github.com/Farid545/Markdown-2/actions/runs/9064945809)***
***You can find unsuccessful test execution via GitHub [here](https://github.com/Farid545/Markdown-2/actions/runs/9065033973)***

## 7. Conclusion:
As it turned out, the unit tests might come in handy for testing code in almost complete isolation when we want to check
throwing an exception or expected behaviour from our method. But the main flaw from current testing is, we would be bothered by additional
configuration, setting up our methods etc. But nowadays, the libraries like JUnit or Mockito might help you with testing to an extent
that even unit testing might be more profitable and easier to make than manual checks.
