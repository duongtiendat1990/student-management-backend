package com.sopen.studentmanagement.services.Impl;

import com.sopen.studentmanagement.message.response.ResponseMessage;
import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.model.Subject;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.UserRepository;
import com.sopen.studentmanagement.security.services.UserPrinciple;
import com.sopen.studentmanagement.services.ClassService;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  ClassService classService;
  @Override
  public User findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public List<User> findAllStudent() {
    return userRepository.findAllStudent();
  }

  @Override
  public List<User> findAllStudentByClassId(Long classId) {
    return userRepository.findAllStudentByClassId(classId);
  }

  @Override
  public User findByEmailIgnoreCase(String email) {
    return userRepository.findByEmailIgnoreCase(email);
  }

  @Override
  public User getUserByAuth() {
    Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long user_id = ((UserPrinciple) userPrinciple).getId();
    return findById(user_id);
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public boolean existsByPhoneNumber(String phoneNumber) {
    return userRepository.existsByPhoneNumber(phoneNumber);
  }

  public void checkConflict(Class aClass) throws ResponseMessage {
    User student = getUserByAuth();
    Set<Class> classes = student.getClasses();
    final Class finalClass = classService.findById(aClass.getId());
    Calendar startTime = finalClass.getStartTime();
    for (Class aClass1 : classes) {
      if (finalClass.equals(aClass1)) {
        throw new ResponseMessage("You have already taken this class");
      }
      if ((startTime.after(aClass1.getStartTime()) && startTime.before(
        aClass1.getEndTime()) && finalClass.getSubject().equals(aClass1.getSubject()))) {
        throw new ResponseMessage("You have already taken class" + aClass1.getName() + "with same subject");
      }
    }
    Class[][] timetable = student.getTimetable();
    int day = startTime.get(Calendar.DAY_OF_WEEK);
    Class[] timetableDay = timetable[day - 2];
    for (int i = finalClass.getStartPeriod() - 1; i < finalClass.getEndPeriod(); i++) {
      if (timetableDay[i] != null) {
        throw new ResponseMessage(
          "This class conflicts timetable with class" + timetableDay[i].getName() + " on " + DayOfWeek.of(
            startTime.get(Calendar.DAY_OF_WEEK) - 1) + " at period" + (i + 1));
      }
    }
  }

  public void enrollClass(Class aClass){
    User student = getUserByAuth();
    Set<Class> classes = student.getClasses();
    classes.add(aClass);
    student.setClasses(classes);
    Set<Subject> subjects = student.getSubjects();
    subjects.add(aClass.getSubject());
    student.setSubjects(subjects);
    Class[][] timetable = student.getTimetable();
    int day = aClass.getStartTime().get(Calendar.DAY_OF_WEEK);
    for (int i = aClass.getStartPeriod()-1; i <aClass.getEndPeriod() ; i++) {
      timetable[day-2][i] = aClass;
    }
    student.setTimetable(timetable);
    save(student);
  }
}
