
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
