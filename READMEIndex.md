

**Features**


Upload JSON File: Select and upload a JSON file containing the secret sharing data.
Process and Calculate: The application processes the file, performs Lagrange interpolation, and displays the constant term (secret).
Prerequisites
A modern web browser (e.g., Chrome, Firefox, Edge)
**Setup Instructions
Clone or Download the Project**

Clone the repository or download the HTML file to your local machine.

bash
Copy code
git clone <repository-url>
Open the HTML File

Open the index.html file in your preferred web browser. Simply double-click the file, and it should open in your default browser.

**Prepare Your JSON File**

Create a JSON file with the following format:

json
Copy code
{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2",
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
}
Make sure your JSON file includes the correct number of points (n) and required shares (k).

**Upload and Process the File**

Click the "Choose File" button to select your JSON file.
Click the "Upload and Process" button to upload the file and calculate the secret.
View the Result

The constant term (secret) will be displayed on the page once processing is complete.

**Notes**
Ensure your JSON file follows the specified format for correct processing.
The application uses Lagrange interpolation to calculate the constant term based on the provided data.
If there are any errors with the JSON format or the processing, an error message will be displayed.
Troubleshooting
No File Selected: Make sure to select a file before clicking the "Upload and Process" button.
Invalid JSON or Processing Error: Ensure that your JSON file is correctly formatted and adheres to the expected structure.
