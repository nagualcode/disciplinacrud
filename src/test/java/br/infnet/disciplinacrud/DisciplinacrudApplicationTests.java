package br.infnet.disciplinacrud;

import br.infnet.disciplinacrud.model.Student;
import br.infnet.disciplinacrud.model.Subject;
import br.infnet.disciplinacrud.model.Grade;
import br.infnet.disciplinacrud.service.StudentService;
import br.infnet.disciplinacrud.service.SubjectService;
import br.infnet.disciplinacrud.service.GradeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DisciplinacrudApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private SubjectService subjectService;

    @MockBean
    private GradeService gradeService;

    @Test
    public void contextLoads() {
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"USER"})
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Test Student");
        student.setCpf("123.456.789-00");
        student.setEmail("test@student.com");
        student.setPhone("1234567890");
        student.setAddress("123 Test St");

        Mockito.when(studentService.save(Mockito.any(Student.class))).thenReturn(student);

        String json = "{\"name\":\"Test Student\",\"cpf\":\"123.456.789-00\",\"email\":\"test@student.com\",\"phone\":\"1234567890\",\"address\":\"123 Test St\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"name\":\"Test Student\",\"cpf\":\"123.456.789-00\",\"email\":\"test@student.com\",\"phone\":\"1234567890\",\"address\":\"123 Test St\"}"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"USER"})
    public void testGetAllStudents() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Test Student");
        student.setCpf("123.456.789-00");
        student.setEmail("test@student.com");
        student.setPhone("1234567890");
        student.setAddress("123 Test St");

        Mockito.when(studentService.findAll()).thenReturn(Collections.singletonList(student));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Test Student\",\"cpf\":\"123.456.789-00\",\"email\":\"test@student.com\",\"phone\":\"1234567890\",\"address\":\"123 Test St\"}]"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"USER"})
    public void testCreateSubject() throws Exception {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");
        subject.setCode("MTH101");

        Mockito.when(subjectService.save(Mockito.any(Subject.class))).thenReturn(subject);

        String json = "{\"name\":\"Math\",\"code\":\"MTH101\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/subjects")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"name\":\"Math\",\"code\":\"MTH101\"}"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"USER"})
    public void testGetAllSubjects() throws Exception {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");
        subject.setCode("MTH101");

        Mockito.when(subjectService.findAll()).thenReturn(Collections.singletonList(subject));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/subjects")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Math\",\"code\":\"MTH101\"}]"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"USER"})
    public void testCreateGrade() throws Exception {
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setValue(8.0);

        Student student = new Student();
        student.setId(1L);
        grade.setStudent(student);

        Subject subject = new Subject();
        subject.setId(1L);
        grade.setSubject(subject);

        Mockito.when(gradeService.save(Mockito.any(Grade.class))).thenReturn(grade);

        String json = "{\"student\":{\"id\":1},\"subject\":{\"id\":1},\"value\":8.0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/grades")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"student\":{\"id\":1,\"name\":null,\"cpf\":null,\"email\":null,\"phone\":null,\"address\":null},\"subject\":{\"id\":1,\"name\":null,\"code\":null},\"value\":8.0}"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"USER"})
    public void testGetApprovedGrades() throws Exception {
        Grade grade = new Grade();
        grade.setValue(8.0);

        Mockito.when(gradeService.findApproved(1L)).thenReturn(Collections.singletonList(grade));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/grades/approved")
                        .param("subjectId", "1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"student\":null,\"subject\":null,\"value\":8.0}]"));
    }

    @Test
    @WithMockUser(username = "teacher", roles = {"USER"})
    public void testGetFailedGrades() throws Exception {
        Grade grade = new Grade();
        grade.setValue(5.0);

        Mockito.when(gradeService.findFailed(1L)).thenReturn(Collections.singletonList(grade));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/grades/failed")
                        .param("subjectId", "1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"student\":null,\"subject\":null,\"value\":5.0}]"));
    }
}
