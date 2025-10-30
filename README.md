
<div align="center">

# 🎯 Trivia _ IN _ Cli - Ultimate CLI Edition

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-4479A1?&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![JDBC](https://img.shields.io/badge/JDBC-API-green?)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
[![License](https://img.shields.io/badge/License-MIT-yellow?)](LICENSE)

**A powerful, feature-rich quiz platform with stunning CLI interface that rivals any GUI!**

[Features](#-features) • [Installation](#-installation) • [Usage](#-usage) • [Architecture](#-architecture) • [Screenshots](#-screenshots) • [Contributing](#-contributing)

</div>

---

## 🌟 Features

### 👨‍🎓 For Students
- ✅ **Take Quizzes** - Interactive quiz-taking experience with real-time feedback
- 📊 **View Results** - Instant score calculation with percentage and grade
- 🏆 **Leaderboard** - Compare performance with other students
- 📈 **Track Progress** - View all your quiz attempts and scores
- 🎨 **Beautiful UI** - Color-coded CLI with animations and progress bars

### 👨‍💼 For Admins
- ➕ **Create Quizzes** - Easy quiz creation with custom titles and time limits
- ❓ **Add Questions** - Multiple-choice questions with 4 options
- ✅ **Publish Quizzes** - Control when quizzes become available to students
- 📊 **View Analytics** - Monitor student performance and quiz statistics
- 🗑️ **Manage Content** - Edit and delete quizzes as needed

### 🎨 UI Highlights
- 🌈 **Rainbow Effects** - Stunning color gradients and animations
- ⚡ **Loading Spinners** - Smooth loading animations
- 🎆 **Fireworks** - Celebratory animations for high scores
- 📊 **Progress Bars** - Visual feedback with rainbow progress bars
- 🏅 **Medal System** - Gold, silver, bronze medals for top performers
- 🎯 **ROG Style** - Gaming-inspired red, black, and cyan color scheme

---

## 🚀 Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java** | Core programming language |
| **JDBC** | Database connectivity |
| **MySQL** | Relational database |
| **ANSI Escape Codes** | Terminal colors and effects |
| **SHA-256** | Password hashing |

---

## 📁 Project Structure

```
QuizCLI/
├── src/
│   ├── Main.java              # Entry point & UI flow
│   ├── DatabaseConfig.java    # Database connection
│   ├── User.java              # User model
│   ├── Quiz.java              # Quiz model
│   ├── Question.java          # Question model
│   ├── AuthService.java       # Authentication logic
│   ├── QuizService.java       # Quiz operations
│   ├── PasswordHasher.java    # Password encryption
│   └── UIHelper.java          # CLI UI utilities (200+ lines of beauty!)
├── database/
│   └── schema.sql             # Database schema
├── mysql-connector-j-8.0.33.jar
└── README.md
```

---

## 🛠️ Installation

### Prerequisites
- **JDK 17+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **MySQL 8.0+** - [Download](https://dev.mysql.com/downloads/mysql/)
- **MySQL Connector/J** - [Download](https://dev.mysql.com/downloads/connector/j/)

### Step 1: Clone the Repository
```
git clone https://github.com/vishnupriyanpr/Trivia_IN_cli.git
cd Trivia_IN_cli/QuizCLI
```

### Step 2: Setup Database
```
# Login to MySQL
mysql -u root -p

# Create database and tables
source database/schema.sql
```

### Step 3: Configure Database Connection
Edit `src/DatabaseConfig.java`:
```
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

### Step 4: Compile & Run
```
# Compile
javac -cp "mysql-connector-j-8.0.33.jar" -d out src/*.java

# Run
java -cp "out;mysql-connector-j-8.0.33.jar" Main
```

**Or use the provided batch file (Windows):**
```
run.bat
```

---

## 🎮 Usage

### First Time Setup
1. **Run the application**
2. **Register** as a Student or Admin
3. **Login** with your credentials

### Default Credentials
```
Admin:
  Username: admin
  Password: admin123

Student:
  Username: student1
  Password: student123
```

### Admin Workflow
1. Create a new quiz
2. Add questions (minimum 1)
3. Publish the quiz
4. View leaderboard and analytics

### Student Workflow
1. View available quizzes
2. Take a quiz
3. View instant results with score and grade
4. Check leaderboard rankings
5. Track your progress

---

## 🏗️ Architecture

### Database Schema
```
users (user_id, username, password_hash, role, created_at)
quizzes (quiz_id, title, time_limit_minutes, is_published, created_at)
questions (question_id, quiz_id, question_text, options, correct_answer)
quiz_attempts (attempt_id, user_id, quiz_id, score, total_questions, attempted_at)
feedback (feedback_id, user_id, quiz_id, rating, comments, created_at)
```

### Design Patterns Used
- **Singleton Pattern** - Database connection management
- **MVC Pattern** - Separation of concerns (Models, Services, UI)
- **DAO Pattern** - Data access abstraction
- **Factory Pattern** - Object creation

---

## 📸 Screenshots

### 🎨 Animated Banner
```
      ╔════════════════════════════════════════════════════════════════════════════════════════╗
      ║                                                                                        ║
      ║   ████████╗██████╗ ██╗██╗   ██╗██╗ █████╗     ██╗███╗   ██╗      ██████╗██╗     ██╗    ║
      ║   ╚══██╔══╝██╔══██╗██║██║   ██║██║██╔══██╗    ██║████╗  ██║     ██╔════╝██║     ██║    ║
      ║      ██║   ██████╔╝██║██║   ██║██║███████║    ██║██╔██╗ ██║     ██║     ██║     ██║    ║
      ║      ██║   ██╔══██╗██║╚██╗ ██╔╝██║██╔══██║    ██║██║╚██╗██║     ██║     ██║     ██║    ║
      ║      ██║   ██║  ██║██║ ╚████╔╝ ██║██║  ██║    ██║██║ ╚████║     ╚██████╗███████╗██║    ║
      ║      ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝  ╚═╝╚═╝  ╚═╝    ╚═╝╚═╝  ╚═══╝      ╚═════╝╚══════╝╚═╝    ║
      ║                                                                                        ║
      ║                              ⚡ ULTIMATE TRIVIA PLATFORM ⚡                           ║
      ║                                                                                        ║
      ╚════════════════════════════════════════════════════════════════════════════════════════╝
      
```

### 🏆 Leaderboard View
```
╔═══════════════════════════════════════════════════════════════════════════╗
║                        🏆 LEADERBOARD 🏆                                  ║
╚═══════════════════════════════════════════════════════════════════════════╝

   RANK   USERNAME             SCORE        ACCURACY   DATE
   ─────────────────────────────────────────────────────────────────────
🥇 1      john_doe             5/5          100.0%     2025-10-29 15:30
🥈 2      jane_smith           4/5          80.0%      2025-10-29 15:25
🥉 3      student1             3/5          60.0%      2025-10-29 15:20
```

### 📊 Score Display
```
╔═══════════════════════════════════════════════════════════════════════════╗
║                          📊 QUIZ RESULTS 📊                               ║
╚═══════════════════════════════════════════════════════════════════════════╝

   ┌──────────────────────────────────────────────────────────────────┐
   │   Score:      5 / 5                                              │
   │   Percentage: 100.00%                                            │
   │   Grade:      OUTSTANDING! 🏆                                    │
   └──────────────────────────────────────────────────────────────────┘

   [████████████████████] 100%
```

---

## 🎨 UI Features

### Color Scheme
- **Primary**: Bright Cyan (`#00FFFF`)
- **Accent**: Bright Red (`#FF0000`)
- **Success**: Bright Green (`#00FF00`)
- **Warning**: Bright Yellow (`#FFFF00`)
- **Error**: Bright Red (`#FF0000`)

### Animations
- ⚡ Loading spinners with 10 frame animation
- 🌈 Rainbow progress bars
- 🎆 Fireworks for perfect scores
- ⭐ Star animations for excellent grades
- 🎭 Glitch effects on headers

---

## 🔐 Security Features

- ✅ **SHA-256 Password Hashing** - Secure password storage
- ✅ **SQL Injection Prevention** - Prepared statements
- ✅ **Input Validation** - Username and password validation
- ✅ **Role-Based Access Control** - Student vs Admin permissions
- ✅ **Session Management** - Secure user sessions

---

## 🧪 Testing

### Sample Data Included
- 1 Admin account
- 2 Student accounts
- 2 Sample quizzes
- 10 Sample questions

### Test Scenarios
1. User registration and login
2. Quiz creation and publishing
3. Question addition
4. Quiz taking and scoring
5. Leaderboard display
6. User score tracking

---

## 📈 Future Enhancements

- [ ] Timer functionality during quiz
- [ ] Question bank management
- [ ] Quiz categories
- [ ] Export results to PDF
- [ ] Email notifications
- [ ] Multi-language support
- [ ] Question difficulty levels
- [ ] Detailed analytics dashboard
- [ ] Quiz scheduling
- [ ] Bulk question import

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the APACHE License 2.0 - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Your Name**
- GitHub: [@vishnupriyanpr](https://github.com/vishnupriyanpr)

---

## 🙏 Acknowledgments

- Inspired by modern quiz platforms
- Built with passion for clean CLI design
- Thanks to the Java and MySQL communities

---

## 📞 Support

If you encounter any issues or have questions:
- Open an [Issue](https://github.com/vishnupriyanpr/Trivia_IN_cli/issues)

---

<div align="center">

### ⭐ Star this repo if you found it helpful!

Made with ❤️ and ☕ by Vishnupriyan P R

