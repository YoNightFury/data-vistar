import { TopicService } from '../services/TopicService.js';
import { CategoryService } from '../services/CategoryService.js';
import { ContentService } from '../services/ContentService.js';
import { CourseService } from '../services/CourseService.js';
import { SubjectService } from '../services/SubjectService.js';
(async function () {
    // Testing CategoryService
    const categoryService = new CategoryService("http://localhost:9000");

    // Test creating a new category
    const newCategory = { name: "New Category"+ Math.floor(Math.random()*1000) };
    const createCategoryResponse = await categoryService.createCategory(newCategory);
    console.log("Created new category:");
    let categoryId = (await createCategoryResponse.json()).message;
    console.log("New category:" + categoryId);


    // Test getting a specific category by ID
    const categoryByIdResponse = await categoryService.getCategoryById(categoryId);
    console.log(`Category with ID ${categoryId}:`);
    console.log(await categoryByIdResponse.json());


    // Test updating an existing category
    const categoryToUpdateId = categoryId;
    const updatedCategoryData = { name: "Updated Category Name"+ Math.floor(Math.random()*1000) };
    const updateCategoryResponse = await categoryService.updateCategory(categoryToUpdateId, updatedCategoryData);
    console.log(`Updated category with ID ${categoryToUpdateId}:`);
    console.log(await updateCategoryResponse.json());

    // Test all category
    const getAllCategoriesResponse = await categoryService.getAllCategories();
    const allCategories = await getAllCategoriesResponse.json();
    console.log(allCategories);

    // Test all categories with paging
    const pagedCategoriesResponse = await categoryService.getCategoriesOfPageLimit(1, 2);
    const pagedCategories = await pagedCategoriesResponse.json();
    console.log(pagedCategories);


    // Testing SubjectService
    const subjectService = new SubjectService("http://localhost:9000");

    // Test creating a new subject
    const newSubject = { name: "New Subject", categoryId };
    const createSubjectResponse = await subjectService.createSubject(newSubject);
    console.log("Created new subject:");
    let subjectId = (await createSubjectResponse.json()).message;
    console.log("New Subject id: " + subjectId);


    // Test getting a specific subject by ID
    const subjectByIdResponse = await subjectService.getSubjectById(subjectId);
    console.log(`Subject with ID ${subjectId}:`);
    console.log(await subjectByIdResponse.json());


    // Test updating an existing subject
    const subjectToUpdateId = subjectId;
    const updatedSubjectData = { name: "Updated Subject Name", categoryId };
    const updateSubjectResponse = await subjectService.updateSubject(subjectToUpdateId, updatedSubjectData);
    console.log(`Updated subject with ID ${subjectToUpdateId}:`);
    console.log(await updateSubjectResponse.json());

    // test get all subjects
    const getAllSubjectsResponse = await subjectService.getAllSubjects();
    const allSubjects = await getAllSubjectsResponse.json();
    console.log(allSubjects);

    // test get paged subjects
    const getPagedSubjectsResponse = await subjectService.getSubjectsOfPageLimit(1, 2);
    const pagedSubjects = await getPagedSubjectsResponse.json();
    console.log(pagedSubjects);

    // Testing CourseService
    const courseService = new CourseService("http://localhost:9000");

    // Test creating a new course
    const newCourse = { name: "New Course", subjectId };
    const createCourseResponse = await courseService.createCourse(newCourse);
    console.log("Created new course:");
    let courseId = (await createCourseResponse.json()).message;
    console.log("New Course id: " + courseId);

    // Test getting a specific course by ID
    const courseByIdResponse = await courseService.getCourseById(courseId);
    console.log(`Course with ID ${courseId}:`);
    console.log(await courseByIdResponse.json());

    // Test updating an existing course
    const courseToUpdateId = courseId;
    const updatedCourseData = { name: "Updated Course Name", subjectId };
    const updateCourseResponse = await courseService.updateCourse(courseToUpdateId, updatedCourseData);
    console.log(`Updated course with ID ${courseToUpdateId}:`);
    console.log(await updateCourseResponse.json());

    // Test getting all courses
    const getAllCoursesResponse = await courseService.getAllCourses();
    const allCourses = await getAllCoursesResponse.json();
    console.log(allCourses);

    // Test getting paged courses
    const getPagedCoursesResponse = await courseService.getCoursesOfPageLimit(1, 2);
    const pagedCourses = await getPagedCoursesResponse.json();
    console.log(pagedCourses);


    // Testing TopicService
    const topicService = new TopicService("http://localhost:9000");

    // Test creating a new topic
    const newTopic = { title: "New Topic", courseId };
    const createTopicResponse = await topicService.createTopic(newTopic);
    console.log("Created new topic:");
    let topicId = (await createTopicResponse.json()).message;
    console.log("New Topic id: " + topicId);

    // Test getting a specific topic by ID
    const topicByIdResponse = await topicService.getTopicById(topicId);
    console.log(`Topic with ID ${topicId}:`);
    console.log(await topicByIdResponse.json());

    // Test updating an existing topic
    const topicToUpdateId = topicId;
    const updatedTopicData = { title: "Updated Topic Title", courseId };
    const updateTopicResponse = await topicService.updateTopic(topicToUpdateId, updatedTopicData);
    console.log(`Updated topic with ID ${topicToUpdateId}:`);
    console.log(await updateTopicResponse.json());

    // Test getting all topics
    const getAllTopicsResponse = await topicService.getAllTopics();
    const allTopics = await getAllTopicsResponse.json();
    console.log(allTopics);

    // Test getting paged topics
    const getPagedTopicsResponse = await topicService.getTopicsOfPageLimit(1, 2);
    const pagedTopics = await getPagedTopicsResponse.json();
    console.log(pagedTopics);


    // Testing ContentService
    const contentService = new ContentService("http://localhost:9000");

    // Test creating a new content
    const newContent = { title: "New Content", body: "Content Body", topicId };
    const createContentResponse = await contentService.createContent(newContent);
    console.log("Created new content:");
    let contentId = (await createContentResponse.json()).message;
    console.log("New Content id: " + contentId);

    // Test getting a specific content by ID
    const contentByIdResponse = await contentService.getContentById(contentId);
    console.log(`Content with ID ${contentId}:`);
    console.log(await contentByIdResponse.json());

    // Test updating an existing content
    const contentToUpdateId = contentId;
    const updatedContentData = { title: "Updated Content Title", body: "Updated Content body", topicId }; // Assuming topic ID 1 exists
    const updateContentResponse = await contentService.updateContent(contentToUpdateId, updatedContentData);
    console.log(`Updated content with ID ${contentToUpdateId}:`);
    console.log(await updateContentResponse.json());

    // Test getting all contents
    const getAllContentsResponse = await contentService.getAllContents();
    const allContents = await getAllContentsResponse.json();
    console.log(allContents);

    // Test getting paged contents
    const getPagedContentsResponse = await contentService.getContentsOfPageLimit(1, 2);
    const pagedContents = await getPagedContentsResponse.json();
    console.log(pagedContents);



})();
