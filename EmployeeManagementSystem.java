
class Employee {

    private int id;
    private String name;
    private String designation;
    private double salary;
    private String department;
    private String email;
    private String phone;

    public Employee(int id, String name, String designation, double salary, String department, String email,
                    String phone) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }
    public int getId() { return id; }

public String getName() { return name; }

public String getDesignation() { return designation; }

public double getSalary() { return salary; }

public String getDepartment() { return department; }

public String getEmail() { return email; }

public String getPhone() { return phone; }

public void setName(String name) { this.name = name; }

public void setDesignation(String designation) { this.designation = designation; }

public void setSalary(double salary) { this.salary = salary; }

public void setDepartment(String department) { this.department = department; }

public void setEmail(String email) { this.email = email; }

public void setPhone(String phone) { this.phone = phone; }

public String toFileString() {
    return String.format("%d,%s,%s,%.2f,%s,%s,%s", 
        id, escape(name), escape(designation), salary, escape(department), escape(email), escape(phone));
}
public static Employee fromFileString(String line) { 

    String[] parts = splitCSV(line);

    if (parts.length < 7) return null;

    try {
        int id = Integer.parseInt(parts[0]);
        String name = unescape(parts[1]);
        String designation = unescape(parts[2]);
        double salary = Double.parseDouble(parts[3]);
        String department = unescape(parts[4]);
        String email = unescape(parts[5]);
        String phone = unescape(parts[6]);

        return new Employee(id, name, designation, salary, department, email, phone);

    } catch (Exception e) {
        return null;
    }
}

public String display() {
    return String.format(
        "ID: %d | Name: %s | Designation: %s | Salary: %.2f | Dept: %s | Email: %s | Phone: %s",
        id, name, designation, salary, department, email, phone
    );
}

private static String escape(String s) {
    if (s == null) return "";
    return s.replace(",", "\\,");
}
private static String unescape(String s) {
    if (s == null) return "";
    return s.replace("\\,", ",");
}

private static String[] splitCSV(String line) {
    List<String> parts = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean escape = false;

    for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (escape) {
            sb.append(c);
            escape = false;
        } else {
            if (c == '\\') escape = true;
            else if (c == ',') {
                parts.add(sb.toString());
                sb = new StringBuilder();
            } else sb.append(c);
        }
    }

    parts.add(sb.toString());
    return parts.toArray(new String[0]);
}
class User {

    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public String toFileString() {
        return username + "," + password + "," + role;
    }

    public static User fromFileString(String line) {
        String[] p = line.split(",", -1);
        if (p.length < 3) return null;
        return new User(p[0], p[1], p[2]);
    }
}
class Department {

    private String name;
    private String manager;

    public Department(String name, String manager) {
        this.name = name;
        this.manager = manager;
    }

    public String getName() { return name; }
    public String getManager() { return manager; }

    public void setManager(String manager) { this.manager = manager; }

    public String toFileString() {
        return escape(name) + "," + escape(manager);
    }

    public static Department fromFileString(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 2) return null;
        return new Department(unescape(parts[0]), unescape(parts[1]));
    }

    private static String escape(String s) { return s.replace(",", "\\,"); }
    private static String unescape(String s) { return s.replace("\\,", ","); }
}
class AttendanceRecord {

private int empId;

private LocalDate date;

private boolean present;

public AttendanceRecord(int empId, LocalDate date, boolean present) {

this.empId empId;

this.date = date;

this.present - present;

}

public int getEmpId() { return empId; }

public LocalDate getDate() { return date; }

public boolean isPresent() { return present; }

public String toFileString() {

I

return String.format("%d,%s, %s", empId, date.toString(), present? "P": "A");

}
public static AttendanceRecord fromFileString(String line) {

    String[] p = line.split(",", -1);
    if (p.length < 3) return null;

    try {
        int id = Integer.parseInt(p[0]);
        LocalDate d = LocalDate.parse(p[1]);
        boolean pres = "P".equalsIgnoreCase(p[2]);
        return new AttendanceRecord(id, d, pres);
    } catch (Exception e) {
        return null;
    }
}
class LeaveRequest {

    private int requestId;
    private int empId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private String status;
    private String decisionBy;

    public LeaveRequest(int requestId, int empId, LocalDate fromDate, LocalDate toDate, String reason, String status, String decisionBy) {
        this.requestId = requestId;
        this.empId = empId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.status = status;
        this.decisionBy = decisionBy;
    }

    public int getRequestId() { return requestId; }
    public int getEmpId() { return empId; }
    public LocalDate getFromDate() { return fromDate; }
    public LocalDate getToDate() { return toDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public String getDecisionBy() { return decisionBy; }

    public void setStatus(String status) { this.status = status; }
    public void setDecisionBy(String decisionBy) { this.decisionBy = decisionBy; }
}
public String toFileString() {
    return String.format("%d,%d,%s,%s,%s,%s,%s",
            requestId,
            empId,
            fromDate.toString(),
            toDate.toString(),
            escape(reason),
            status,
            decisionBy == null ? "" : decisionBy);
}
public static LeaveRequest fromFileString(String line) {

    String[] p = line.split(",", -1);

    if (p.length < 7) return null;

    try {
        int rid = Integer.parseInt(p[0]);
        int eid = Integer.parseInt(p[1]);
        LocalDate from = LocalDate.parse(p[2]);
        LocalDate to = LocalDate.parse(p[3]);
        String reason = unescape(p[4]);
        String status = p[5];
        String decisionBy = p[6].isEmpty() ? null : p[6];

        return new LeaveRequest(rid, eid, from, to, reason, status, decisionBy);
    } catch (Exception e) {
        return null;
    }
}

private static String escape(String s) { 
    return s.replace(",", "\\,"); 
}

private static String unescape(String s) { 
    return s.replace("\\,", ","); 
}
public static Performance fromFileString(String line) {

    String[] p = line.split(",", -1);

    if (p.length < 4) return null;

    try {
        int eid = Integer.parseInt(p[0]);
        LocalDate d = LocalDate.parse(p[1]);
        int r = Integer.parseInt(p[2]);
        String notes = unescape(p[3]);
        return new Performance(eid, d, r, notes);
    } catch (Exception e) {
        return null;
    }
}

private static String escape(String s) { 
    return s.replace(",", "\\,"); 
}

private static String unescape(String s) { 
    return s.replace("\\,", ","); 
}

public Payroll(int empId, LocalDate date, double basic, double allowances, double deductions, double netSalary) {
    this.empId = empId;
    this.date = date;
    this.basic = basic;
    this.allowances = allowances;
    this.deductions = deductions;
    this.netSalary = netSalary;
}

public String toFileString() {
    return String.format("%d,%s,%.2f,%.2f,%.2f,%.2f", empId, date.toString(), basic, allowances, deductions, netSalary);
}
public static Payroll fromFileString(String line) {
    String[] p = line.split(",", -1);
    if (p.length < 6) return null;

    try {
        int eid = Integer.parseInt(p[0]);
        LocalDate d = LocalDate.parse(p[1]);
        double b = Double.parseDouble(p[2]);
        double a = Double.parseDouble(p[3]);
        double dd = Double.parseDouble(p[4]);
        double n = Double.parseDouble(p[5]);

        return new Payroll(eid, d, b, a, dd, n);
    } catch (Exception e) {
        return null;
    }
}
public class EmployeeManagementSystem {

    private static final String EMP_FILE = "employees.txt";
    private static final String USER_FILE = "users.txt";
    private static final String DEPT_FILE = "departments.txt";
    private static final String ATT_FILE = "attendance.txt";
    private static final String LEAVE_FILE = "leaves.txt";
    private static final String PERF_FILE = "performance.txt";
    private static final String PAY_FILE = "payroll.txt";

    private Map<Integer, Employee> employees = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, Department> departments = new HashMap<>();
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();
    private List<LeaveRequest> leaveRequests = new ArrayList<>();
    private List<Performance> performanceRecords = new ArrayList<>();
    private List<Payroll> payrollRecords = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);
    private User currentUser = null;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        EmployeeManagementSystem app = new EmployeeManagementSystem();
        app.bootstrap();
        app.run();
    }
}
public static void main(String[] args) {
    EmployeeManagementSystem app = new EmployeeManagementSystem();
    app.bootstrap();
    app.run();
}

private void bootstrap() {
    ensureFilesExist();
    loadUsers();
    loadDepartments();
    loadEmployees();
    loadAttendance();
    loadLeaves();
    loadPerformance();
    loadPayroll();

    if (users.isEmpty()) {
        System.out.println("No users detected. Creating default admin user -> username: admin, password: admin");
        User admin = new User("admin", "admin", "ADMIN");
        users.put(admin.getUsername(), admin);
        saveUsers();
    }

    if (departments.isEmpty()) {
        Department d = new Department("General", "Admin");
        departments.put(d.getName(), d);
        saveDepartments();
    }
}
private void run() {
    printHeader();

    boolean authenticated = false;
    while (!authenticated) {
        authenticated = loginFlow();
    }

    int choice;
    do {
        showMainMenu();
        choice = readInt("Select an option: ");
        switch (choice) {
            case 1: employeeManagementMenu(); break;
            case 2: departmentManagementMenu(); break;
            case 3: attendanceMenu(); break;
            case 4: leaveManagementMenu(); break;
            case 5: performanceMenu(); break;
            case 6: payrollMenu(); break;
            case 7: reportsMenu(); break;
            case 8: userManagementMenu(); break;
            case 9: backupExportMenu(); break;
            case 0:
                System.out.println("Exiting application. Persisting all changes.");
                persistAll();
                break;
            default:
                
                break;
        }
    } while (choice != 0);
    System.err.println("Goodbye");
}
private void printHeader() {
    System.out.println("====");
    System.out.println("Enterprise");
    System.out.println("Employee Management System");
    System.out.println("============");
}
private boolean loginFlow() {

    System.out.println("Login: ");
    String uname = readString("Username: ");
    String pwd = readString("Password: ");

    User u = users.get(uname);

    if (u != null && u.getPassword().equals(pwd)) {
        currentUser = u;
        System.out.printf("Authentication successful. Role: %s\n", u.getRole());
        return true;
    } else {
        System.out.println("Invalid credentials. Please try again.");
        return false;
    }

}
private void showMainMenu() {

    System.out.println("\n--- Main Menu ---");
    System.out.println("1. Employee Management");
    System.out.println("2. Department Management");
    System.out.println("3. Attendance");
    System.out.println("4. Leave Management");
    System.out.println("5. Performance Management");
    System.out.println("6. Payroll");
    System.out.println("7. Reports");
    System.out.println("8. User Management (Admin only)");
    System.out.println("9. Backup / Export");
    System.out.println("0. Exit");

}
private void employeeManagementMenu() {

    System.out.println("\n--- Employee Management ---");
    System.out.println("1. Add Employee");
    System.out.println("2. View All Employees");
    System.out.println("3. Search Employee (ID)");
    System.out.println("4. Search Employee (Name)");
    System.out.println("5. Update Employee");
    System.out.println("6. Delete Employee");
    System.out.println("7. Assign Employee to Department");
    System.out.println("8. Sort employees by (1) Name (2) Salary (3) Rating");
    System.out.println("9. Import employees from CSV file");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: if (authorize("ADMIN", "HR")) addEmployee(); else unauthorized(); break;
        case 2: viewAllEmployees(); break;
        case 3: searchEmployeeById(); break;
        case 4: searchEmployeeByName(); break;
        case 5: if (authorize("ADMIN", "HR")) updateEmployee(); else unauthorized(); break;
        case 6: if (authorize("ADMIN")) deleteEmployee(); else unauthorized(); break;
        case 7: if (authorize("ADMIN", "HR")) assignDepartment(); else unauthorized(); break;
        case 8: sortEmployeesMenu(); break;
        case 9: if (authorize("ADMIN")) importEmployeesFromCSV(); else unauthorized(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }

}
private void departmentManagementMenu() {

    System.out.println("\n--- Department Management ---");
    System.out.println("1. Add Department");
    System.out.println("2. View Departments");
    System.out.println("3. Update Department Manager");
    System.out.println("4. Delete Department");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: if (authorize("ADMIN")) addDepartment(); else unauthorized(); break;
        case 2: viewDepartments(); break;
        case 3: if (authorize("ADMIN")) updateDepartmentManager(); else unauthorized(); break;
        case 4: if (authorize("ADMIN")) deleteDepartment(); else unauthorized(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }

}
private void attendanceMenu() {

    System.out.println("\n--- Attendance");
    System.out.println("1. Mark Attendance (Today)");
    System.out.println("2. View Attendance for Employee");
    System.out.println("3. Generate Attendance Report (by date range)");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: if (authorize("ADMIN", "HR")) markAttendanceToday(); else unauthorized(); break;
        case 2: viewAttendanceForEmployee(); break;
        case 3: generateAttendanceReport(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }

}
private void leaveManagementMenu() {

    System.out.println("\n--- Leave Management ---");
    System.out.println("1. Apply Leave (Employee)");
    System.out.println("2. View Leave Requests");
    System.out.println("3. Approve/Reject Leave (Admin/HR)");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: applyLeave(); break;
        case 2: viewLeaveRequests(); break;
        case 3: if (authorize("ADMIN", "HR")) processLeaveRequests(); else unauthorized(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }

}
private void performanceMenu() {

    System.out.println("\n--- Performance---");
    System.out.println("1. Add Performance Rating");
    System.out.println("2. View Performance for Employee");
    System.out.println("3. List top performers");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: if (authorize("ADMIN", "HR")) addPerformance(); else unauthorized(); break;
        case 2: viewPerformanceForEmployee(); break;
        case 3: listTopPerformers(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }

}
private void payrollMenu() {

    System.out.println("\n--- Payroll ---");
    System.out.println("1. Generate Salary Slip (Employee ID)");
    System.out.println("2. View Payroll Records");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: if (authorize("ADMIN")) generateSalarySlip(); else unauthorized(); break;
        case 2: viewPayrollRecords(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }

}
private void reportsMenu() {

    System.out.println("\n--- Reports ---");
    System.out.println("1. Attendance Percentage Report (by dept or all)");
    System.out.println("2. Department Summary (count, avg salary)");
    System.out.println("3. Top Earners");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: attendancePercentageReport(); break;
        case 2: departmentSummaryReport(); break; // kept name idea but fixed space for compilation
        case 3: topEarnersReport(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }

}
private void userManagementMenu() {

    if (!authorize("ADMIN")) { unauthorized(); return; }

    System.out.println("\n--- User Management (Admin)");
    System.out.println("1. Create User");
    System.out.println("2. Delete User");
    System.out.println("3. List Users");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: createUser(); break;
        case 2: deleteUser(); break;
        case 3: listUsers(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }
}
private void backupExportMenu() {

    System.out.println("\n--- Backup / Export ---");
    System.out.println("1. Export all data to timestamped backup file");
    System.out.println("2. Export employees only");
    System.out.println("0. Back");

    int c = readInt("Choose: ");

    switch (c) {
        case 1: exportAll(); break;
        case 2: exportEmployeesOnly(); break;
        case 0: return;
        default: System.out.println("Invalid option."); break;
    }
}
private void addEmployee() {

    System.out.println("Add Employee, please provide details.");

    int id = readInt("ID (numeric): ");

    if (employees.containsKey(id)) {
        System.out.println("Employee with this ID already exists.");
        return;
    }

    String name = readString("Name: ");
    String designation = readString("Designation: ");
    double salary = readDouble("Salary (Taka): ");
    String dept = chooseDepartmentPrompt();
    String email = readString("Email: ");
    String phone = readString("Phone: ");

    Employee e = new Employee 
}
private void viewAllEmployees() {

    if (employees.isEmpty()) {
        System.out.println("No employees available.");
        return;
    }

    System.out.println("Employees:");

    employees.values().stream()
        .sorted(Comparator.comparing(Employee::getId))
        .forEach(e -> System.out.println(e.display()));

}

private void searchEmployeeById() {

    int id = readInt("Enter Employee ID: ");
    Employee e = employees.get(id);

    if (e == null)
        System.out.println("Employee not found.");
    else
        System.out.println(e.display());

}
private void searchEmployeeByName() {

    String name = readString("Enter name (partial allowed): ").toLowerCase();
    List<Employee> found = new ArrayList<>();

    for (Employee e : employees.values()) {
        if (e.getName().toLowerCase().contains(name)) found.add(e);
    }

    if (found.isEmpty()) {
        System.out.println("No matching employees.");
    } else {
        found.forEach(emp -> System.out.println(emp.display()));
    }

}

private void updateEmployee() {

    int id = readInt("Enter Employee ID to update: ");
    E

}
System.out.println("Leave blank to keep existing value.");

String name = readOptionalString(String.format("Name [%s]: ", e.getName()));
String desig = readOptionalString(String.format("Designation [%s]: ", e.getDesignation()));
String salaryStr = readOptionalString(String.format("Salary [%.2f]: ", e.getSalary()));
String dept = chooseDepartmentPromptOptional(e.getDepartment());
String email = readOptionalString(String.format("Email [%s]: ", e.getEmail()));
String phone = readOptionalString(String.format("Phone [%s]: ", e.getPhone()));

if (!name.isEmpty()) e.setName(name);
if (!desig.isEmpty()) e.setDesignation(desig);

if (!salaryStr.isEmpty()) {
    try {
        e.setSalary(Double.parseDouble(salaryStr));
    } catch (NumberFormatException ex) {
        System.out.println("Invalid salary, keeping previous.");
    }
}

if (!dept.isEmpty()) e.setDepartment(dept);
if (!email.isEmpty()) e.setEmail(email);
if (!phone.isEmpty()) e.setPhone(phone);

saveEmployees();
System.out.println("Employee updated.");
}
private void deleteEmployee() {

    int id = readInt("Enter Employee ID to delete: ");

    if (!employees.containsKey(id)) {
        System.out.println("Employee not found.");
        return;
    }

    employees.remove(id);

    
    attendanceRecords.removeIf(ar -> ar.getEmpId() == id);
    performanceRecords.removeIf(pr -> pr.getEmpId() == id);
    payrollRecords.removeIf(pay -> pay != null && pay.toFileString().startsWith(id + ","));
    leaveRequests.removeIf(lr -> lr.getEmpId() == id);

    saveEmployees();
    saveAttendance();
    savePerformance();
    savePayroll();
    saveLeaves();

    System.out.println("Employee and related records removed.");
}
private void assignDepartment() {

    int id = readInt("Employee ID: ");

    Employee e = employees.get(id);

    if (e == null) {
        System.out.println("Employee not found.");
        return;
    }

    String dept = chooseDepartmentPrompt();

    e.setDepartment(dept);

    saveEmployees();

    System.out.println("Department updated.");
}
private void sortEmployeesMenu() {

    System.out.println("Sort by: 1. Name 2. Salary 3. Rating");

    int c = readInt("Choose: ");

    List<Employee> list = new ArrayList<>(employees.values());

    switch (c) {

        case 1:
            list.sort(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER));
            list.forEach(e -> System.out.println(e.display()));
            break;

        case 2:
            list.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
            list.forEach(e -> System.out.println(e.display()));
            break;

        case 3:
            Map<Integer, Double> avgRating = new HashMap<>();
            for (Performance p : performanceRecords) {
                avgRating.putIfAbsent(p.getEmpId(), 0.0);
                avgRating.put(p.getEmpId(), avgRating.get(p.getEmpId()) + p.getRating());
            }

            Map<Integer, Integer> count = new HashMap<>();
            for (Performance p : performanceRecords) {
                count.putIfAbsent(p.getEmpId(), 0);
                count.put(p.getEmpId(), count.get(p.getEmpId()) + 1);
            }

            list.sort((a, b) -> {
                double avgA = 0.0;
                double avgB = 0.0;
                if (avgRating.containsKey(a.getId())) {
                    avgA = avgRating.get(a.getId()) / count.get(a.getId());
                }
                if (avgRating.containsKey(b.getId())) {
                    avgB = avgRating.get(b.getId()) / count.get(b.getId());
                }
                return Double.compare(avgB, avgA);
            });

            list.forEach(e -> System.out.println(e.display()));
            break;
    }
}
private void importEmployeesFromCSV() {

    String path = readString("Enter CSV file path: ");
    Path p = Paths.get(path);

    if (!Files.exists(p)) {
        System.out.println("File not found.");
        return;
    }

    try (BufferedReader br = Files.newBufferedReader(p)) {
        String line;
        int added = 0;

        while ((line = br.readLine()) != null) {

            // expected: id, name, designation, salary, department, email, phone
            Employee e = Employee.fromFileString(line);

            if (e != null && !employees.containsKey(e.getId())) {
                employees.put(e.getId(), e);
                added++;
            }
        }

        saveEmployees();
        System.out.printf("Import complete. %d new employees added.\n", added);

    } catch (IOException ex) {
        System.out.println("Import failed: " + ex.getMessage());
    }
}
// Service CRUD

private void addDepartment() {

    String name = readString("Department name: ");

    if (departments.containsKey(name)) {
        System.out.println("Department already exists.");
        return;
    }

    String manager = readString("Manager name: ");

    Department d = new Department(name, manager);
    departments.put(name, d);

    saveDepartments();

    System.out.println("Department added.");
}

private void viewDepartments() {

    if (departments.isEmpty()) {
        System.out.println("No departments configured.");
        return;
    }

    departments.values().forEach(d -> System.out.println("Department: " + d.getName() + " | Manager: " + d.getManager()));
}
private void updateDepartmentManager() {

    String name = readString("Department name: ");
    Department d = departments.get(name);

    if (d == null) {
        System.out.println("Department not found.");
        return;
    }

    String newManager = readString("New Manager name: ");
    d.setManager(newManager);

    saveDepartments();
    System.out.println("Department updated.");
}

private void deleteDepartment() {

    String name = readString("Department name to delete: ");

    if (!departments.containsKey(name)) {
        System.out.println("Department not found.");
        return;
    }

    // Reassign affected employees to the default 'General'
    departments.remove(name);

    for (Employee e : employees.values()) {
        if (name.equals(e.getDepartment())) {
            e.setDepartment("General");
        }
    }

    saveDepartments();
    saveEmployees();

    System.out.println("Department deleted and employees reassigned to 'General'");
}
private String chooseDepartmentPrompt() {

    System.out.println("Available departments:");
    int i = 1;
    List<String> deptNames = new ArrayList<>(departments.keySet());

    for (String dn : deptNames) {
        System.out.printf("%d. %s\n", i++, dn);
    }

    System.out.printf("%d. Create new department\n", i++);

    int choice = readInt("Choose department number: ");

    if (choice >= 1 && choice <= deptNames.size()) {
        return deptNames.get(choice - 1);
    }

    if (choice == deptNames.size() + 1) {
        String newDept = readString("New Department name: ");
        String manager = readString("Manager name: ");
        Department d = new Department(newDept, manager);
        departments.put(newDept, d);
        saveDepartments();
        return newDept;
    }

    System.out.println("Invalid choice, defaulting to 'General'.");
    return "General";
}
private String chooseDepartmentPromptOptional(String current) {

    System.out.printf("Current department: %s\n", current);
    System.out.println("Leave blank to keep. Otherwise choose department as below.");
    System.out.println("Available departments:");

    int i = 1;
    List<String> deptNames = new ArrayList<>(departments.keySet());

    for (String dn : deptNames) {
        System.out.printf("%d. %s\n", i++, dn);
    }

    System.out.printf("%d. Create new department\n", i++);

    String sel = readOptionalString("Enter number or blank: ");

    if (sel.isEmpty()) return "";

    try {
        int choice = Integer.parseInt(sel);

        if (choice >= 1 && choice <= deptNames.size()) {
            return deptNames.get(choice - 1);
        }

        if (choice == deptNames.size() + 1) {
            String newDept = readString("New Department name: ");
            String manager = readString("Manager name: ");
            Department d = new Department(newDept, manager);
            departments.put(newDept, d);
            saveDepartments();
            return newDept;
        }

    } catch (NumberFormatException nfe) {
        System.out.println("Invalid input. Keeping current.");
    }

    return "";
}
private void markAttendanceToday() {

    LocalDate today = LocalDate.now();
    System.out.println("Mark attendance for date: " + today);

    for (Employee e : employees.values()) {

        String in = readOptionalString(String.format(
                "Employee %d %s (P/A, default P): ",
                e.getId(), e.getName()
        ));

        boolean present = !("A".equalsIgnoreCase(in) || "absent".equalsIgnoreCase(in));

        AttendanceRecord ar = new AttendanceRecord(e.getId(), today, present);
        attendanceRecords.add(ar);
    }

    saveAttendance();
    System.out.println("Attendance marked for today.");
}
private void viewAttendanceForEmployee() {

    int id = readInt("Employee ID: ");
    List<AttendanceRecord> recs = new ArrayList<>();

    for (AttendanceRecord ar : attendanceRecords)
        if (ar.getEmpId() == id) recs.add(ar);

    if (recs.isEmpty()) {
        System.out.println("No attendance records found.");
    } else {
        recs.sort(Comparator.comparing(AttendanceRecord::getDate));
        for (AttendanceRecord r : recs) {
            System.out.printf("%s - %s\n", r.getDate().toString(), r.isPresent() ? "Present" : "Absent");
        }
    }
}
private void generateAttendanceReport() {

    String froms = readString("From date (yyyy-mm-dd): ");
    String tos = readString("To date (yyyy-mm-dd): ");

    LocalDate from = LocalDate.parse(froms);
    LocalDate to = LocalDate.parse(tos);

    Map<Integer, Integer> presents = new HashMap<>();
    Map<Integer, Integer> totals = new HashMap<>();

    for (AttendanceRecord ar : attendanceRecords) {

        if (ar.getDate().isBefore(from) || ar.getDate().isAfter(to)) continue;

        totals.putIfAbsent(ar.getEmpId(), 0);
        totals.put(ar.getEmpId(), totals.get(ar.getEmpId()) + 1);

        if (ar.isPresent()) {
            presents.putIfAbsent(ar.getEmpId(), 0);
            presents.put(ar.getEmpId(), presents.get(ar.getEmpId()) + 1);
        }
    }

    System.out.println("Attendance Report:");

    for (Employee e : employees.values()) {

        int total = totals.getOrDefault(e.getId(), 0);
        int pres = presents.getOrDefault(e.getId(), 0);
        double pct = total == 0 ? 0.0 : (pres * 100.0 / total);

        System.out.printf("Emp %d %s | Present: %d %d (%.2f%%)\n", e.getId(), e.getName(), pres, total, pct);
    }
}
// Leave Management

private void applyLeave() {

    int empId = readInt("Your Employee ID: ");

    if (!employees.containsKey(empId)) {
        System.out.println("Employee not found.");
        return;
    }

    String froms = readString("From date (yyyy-mm-dd): ");
    String toS = readString("To date (yyyy-mm-dd): ");
    String reason = readString("Reason: ");

    LocalDate from = LocalDate.parse(froms);
    LocalDate to = LocalDate.parse(toS);

    int nextReqId = leaveRequests.stream().mapToInt(LeaveRequest::getRequestId).max().orElse(1000) + 1;
    LeaveRequest lr = new LeaveRequest(nextReqId, empId, from, to, reason, "PENDING", null);
    leaveRequests.add(lr);
    saveLeaves();
    System.out.println("Leave request submitted with ID: " + nextReqId);
}
private void viewLeaveRequests() {

    if (leaveRequests.isEmpty()) {
        System.out.println("No leave requests.");
        return;
    }

    for (LeaveRequest lr : leaveRequests) {
        System.out.printf(
            "ReqID: %d | Emp: %d | %s to %s | Reason: %s | Status: %s | DecisionBy: %s\n",
            lr.getRequestId(), lr.getEmpId(), lr.getFromDate(), lr.getToDate(), lr.getReason(), lr.getStatus(), lr.getDecisionBy()
        );
    }
}
private void processLeaveRequests() {

    viewLeaveRequests();

    int id = readInt("Enter Request ID to process: ");

    Optional<LeaveRequest> maybe = leaveRequests.stream().filter(lr -> lr.getRequestId() == id).findFirst();

    if (!maybe.isPresent()) {
        System.out.println("Request not found.");
        return;
    }

    LeaveRequest lr = maybe.get();

    System.out.println("1. Approve 2. Reject 0. Cancel");
    int choice = readInt("Choose: ");

    if (choice == 1) {
        lr.setStatus("APPROVED");
        lr.setDecisionBy(currentUser.getUsername());
        System.out.println("Leave approved.");
    } else if (choice == 2) {
        lr.setStatus("REJECTED");
        lr.setDecisionBy(currentUser.getUsername());
        System.out.println("Leave rejected.");
    } else {
        System.out.println("Cancelled.");
        return;
    }

    saveLeaves();
}
//

Performance

private void addPerformance() {

}

int empId = readInt("Employee ID to rate: ");

if (!employees.containsKey(empId)) {

System.out.println("Employee not found.");

return;

}

int rating readInt("Rating (1-5): ");

if (rating < 1 || rating > 5) {

System.out.println("Invalid rating.");

return;

}

String notes readString("Notes: ");

Performance p = new Performance(empId, LocalDate.now(), rating, notes);

performanceRecords.add(p);

savePerformance();

System.out.println("Performance recorded.");
}
private void viewPerformanceForEmployee() {

    int empId = readInt("Employee ID: ");

    List<Performance> recs = new ArrayList<>();

    for (Performance p : performanceRecords) 
        if (p.getEmpId() == empId) recs.add(p);

    if (recs.isEmpty()) 
        System.out.println("No performance records.");
    else {
        recs.sort(Comparator.comparing(Performance::getDate));
        for (Performance r : recs) {
            System.out.printf("%s | Rating: %d | Notes: %s\n", r.getDate().toString(), r.getRating(), r.getNotes());
        }
    }
}

private void listTopPerformers() {

    Map<Integer, List<Integer>> map = new HashMap<>();

    for (Performance p : performanceRecords) {
        map.putIfAbsent(p.getEmpId(), new ArrayList<>());
        map.get(p.getEmpId()).add(p.getRating());
    }

    List<Map.Entry<Integer, Double>> avgList = new ArrayList<>();

    for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
        double avg = e.getValue().stream().mapToInt(i -> i).average().orElse(0.0);
        avgList.add(new AbstractMap.SimpleEntry<>(e.getKey(), avg));
    }

    avgList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

    System.out.println("Top performers:");
    int limit = Math.min(10, avgList.size());

    for (int i = 0; i < limit; i++) {
        int empId = avgList.get(i).getKey();
        double avg = avgList.get(i).getValue();
        Employee e = employees.get(empId);
        if (e != null) System.out.printf("%d. %s | AvgRating: %.2f\n", i + 1, e.getName(), avg);
    }
}
//Payroll

private void generateSalarySlip() {

    int empId = readInt("Employee ID: ");
    Employee e = employees.get(empId);

    if (e == null) {
        System.out.println("Employee not found.");
        return;
    }

    LocalDate date = LocalDate.now();

    double basic = e.getSalary();

    // Business logic for allowances and deductions (illustrative)
    double hra = basic * 0.20; // housing allowance
    double medical = 1500; // fixed
    double conveyance = 800;
    double allowances = hra + medical + conveyance;

    // Deductions: tax and provident fund
    double tax = computeTax(basic + allowances);
    double pf = basic * 0.05;
    double deductions = tax + pf;

    double net = basic + allowances - deductions;

    Payroll p = new Payroll(empId, date, basic, allowances, deductions, net);
    payrollRecords.add(p);
    savePayroll();
}
// Write salary slip file

String slipFile = String.format("slip_%d_%s.txt", empId, date.toString());

try (BufferedWriter bw = new BufferedWriter(new FileWriter(slipFile))) {

    bw.write("Salary Slip\n");
    bw.write("Date: " + date.toString() + "\n");
    bw.write("Employee: " + e.getName() + " (ID: " + e.getId() + ")\n");
    bw.write("Designation: " + e.getDesignation() + "\n");
    bw.write(String.format("Basic: %.2f\n", basic));
    bw.write(String.format("Allowances: %.2f\n", allowances));
    bw.write(String.format("Deductions: %.2f\n", deductions));
    bw.write(String.format("Net Salary: %.2f\n", net));

    bw.flush();
    System.out.println("Salary slip generated: " + slipFile);

} catch (IOException ex) {
    System.out.println("Failed to write salary slip: " + ex.getMessage());
}
private double computeTax(double gross) {

    // Simple progressive tax slab demo
    if (gross <= 25000) return gross * 0.0;
    if (gross <= 50000) return (gross - 25000) * 0.05;
    if (gross <= 100000) return (gross - 50000) * 0.10 + 25000 * 0.05;
    return (gross - 100000) * 0.15 + 50000 * 0.10 + 25000 * 0.05;

}

private void viewPayrollRecords() {

    if (payrollRecords.isEmpty()) {
        System.out.println("No payroll records.");
        return;
    }

    for (Payroll p : payrollRecords) {
        System.out.println(p.toFileString());
    }

}
private void attendancePercentageReport() {

    System.out.println("1. All employees 2. By Department");
    int c = readInt("Choose: ");

    if (c == 1) {
        Map<Integer, Integer> total = new HashMap<>();
        Map<Integer, Integer> pres = new HashMap<>();

        for (AttendanceRecord ar : attendanceRecords) {
            total.put(ar.getEmpId(), total.getOrDefault(ar.getEmpId(), 0) + 1);
            if (ar.isPresent()) pres.put(ar.getEmpId(), pres.getOrDefault(ar.getEmpId(), 0) + 1);
        }

        System.out.println("Attendance %:");
        for (Employee e : employees.values()) {
            int tot = total.getOrDefault(e.getId(), 0);
            int p = pres.getOrDefault(e.getId(), 0);
            double pct = tot == 0 ? 0.0 : (p * 100.0 / tot);
            System.out.printf("%s | %.2f%%\n", e.getName(), pct);
        }

    } else if (c == 2) {

        viewDepartments();
        String dept = readString("Department name: ");

        Map<Integer, Integer> total = new HashMap<>();
        Map<Integer, Integer> pres = new HashMap<>();

        for (AttendanceRecord ar : attendanceRecords) {
            total.put(ar.getEmpId(), total.getOrDefault(ar.getEmpId(), 0) + 1);
            if (ar.isPresent()) pres.put(ar.getEmpId(), pres.getOrDefault(ar.getEmpId(), 0) + 1);
        }

        System.out.println("Attendance % for dept " + dept);
    }
}
private void departmentSummaryReport() {

    System.out.println("Department Summary");

    for (Department d : departments.values()) {

        long count = employees.values().stream().filter(e -> d.getName().equals(e.getDepartment()))
                .count();

        double avg = employees.values().stream().filter(e -> d.getName().equals(e.getDepartment()))
                .mapToDouble(Employee::getSalary).average().orElse(0.0);

        System.out.printf("Dept: %s | Count: %d | Avg Salary: %.2f\n", d.getName(), count, avg);
    }
}
private void topEarnersReport() {

    List<Employee> list = new ArrayList<>(employees.values());
    list.sort(Comparator.comparingDouble(Employee::getSalary).reversed());

    System.out.println("Top Earners:");
    int limit = Math.min(10, list.size());

    for (int i = 0; i < limit; i++) {
        Employee e = list.get(i);
        System.out.printf("%d. %s | %.2f | Dept: %s\n", i + 1, e.getName(), e.getSalary(), e.getDepartment());
    }
}

// User Management

private void createUser() {

    String uname = readString("New username: ");
    if (users.containsKey(uname)) { 
        System.out.println("User exists."); 
        return; 
    }

    String pwd = readString("Password: ");
    String role = readString("Role (ADMIN/HR): ").toUpperCase();

    if (!"ADMIN".equals(role) && !"HR".equals(role)) { 
        System.out.println("Invalid role."); 
        return; 
    }

    User u = new User(uname, pwd, role);
    users.put(uname, u);
    saveUsers();

    System.out.println("User created.");
}
private void deleteUser() {

    String uname = readString("Username to delete: ");
    if (!users.containsKey(uname)) { 
        System.out.println("User not found."); 
        return; 
    }

    users.remove(uname);
    saveUsers();
    System.out.println("User removed.");
}

private void listUsers() {

    System.out.println("Users:");
    users.values().forEach(u -> System.out.println(u.getUsername() + " | Role: " + u.getRole()));
}
// Backup / Export

private void exportAll() {

    String ts = LocalDate.now().toString();
    String out = "backup_all_" + ts + ".txt";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {

        bw.write("--- USERS ---\n");
        for (User u : users.values()) bw.write(u.toFileString() + "\n");

        bw.write("\n=== DEPARTMENTS ===\n");
        for (Department d : departments.values()) bw.write(d.toFileString() + "\n");

        bw.write("\n=== EMPLOYEES ===\n");
        for (Employee e : employees.values()) bw.write(e.toFileString() + "\n");

        bw.write("\n=== ATTENDANCE ===\n");
        for (AttendanceRecord a : attendanceRecords) bw.write(a.toFileString() + "\n");

        bw.write("\n=== LEAVES ===\n");
        for (LeaveRequest l : leaveRequests) bw.write(l.toFileString() + "\n");

        bw.write("\n=== PERFORMANCE ===\n");
        for (Performance p : performanceRecords) bw.write(p.toFileString() + "\n");

        bw.write("\n--- PAYROLL ---\n");
        for (Payroll p : payrollRecords) bw.write(p.toFileString() + "\n");

        System.out.println("Exported all data to " + out);

    } catch (IOException ex) {
        System.out.println("Export failed: " + ex.getMessage());
    }
}
private void exportEmployeesOnly() {

    String out = "employees_export_" + LocalDate.now().toString() + ".txt";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {

        for (Employee e : employees.values()) bw.write(e.toFileString() + "\n");

        System.out.println("Employees exported to " + out);

    } catch (IOException ex) {

        System.out.println("Export failed: " + ex.getMessage());

    }

}

// Persistence Helpers

private void persistAll() {

    saveUsers();
    saveDepartments();
    saveEmployees();
    saveAttendance();
    saveLeaves();
    savePerformance();
    savePayroll();

}
private void saveEmployees() {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMP_FILE))) {

        for (Employee e : employees.values()) bw.write(e.toFileString() + "\n");

    } catch (IOException ex) {

        System.out.println("Failed to save employees: " + ex.getMessage());

    }

}

private void loadEmployees() {

    employees.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(EMP_FILE))) {

        String line;

        while ((line = br.readLine()) != null) {

            Employee e = Employee.fromFileString(line);
            if (e != null) employees.put(e.getId(), e);

        }

    } catch (IOException ex) {

        // missing file or empty start

    }

}
private void saveUsers() {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {

        for (User u : users.values()) bw.write(u.toFileString() + "\n");

    } catch (IOException ex) {

        System.out.println("Failed to save users: " + ex.getMessage());

    }

}

private void loadUsers() {

    users.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {

        String line;

        while ((line = br.readLine()) != null) {

            User u = User.fromFileString(line);
            if (u != null) users.put(u.getUsername(), u);

        }

    } catch (IOException ex) {

        // ignore

    }

}
private void saveAttendance() {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ATT_FILE))) {
        for (AttendanceRecord a : attendanceRecords) bw.write(a.toFileString() + "\n");
    } catch (IOException ex) {
        System.out.println("Failed to save attendance: " + ex.getMessage());
    }

}

private void loadAttendance() {

    attendanceRecords.clear();

    try (BufferedReader br = new BufferedReader(new FileReader(ATT_FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            AttendanceRecord a = AttendanceRecord.fromFileString(line);
            if (a != null) attendanceRecords.add(a);
        }
    } catch (IOException ex) {
        // ignore
    }

}
