import { validateInteger } from "./Validator.js";
export class Course {
    /**
     * 
     * @param {string} name 
     * @param {number} subjectId 
     */
    constructor(name, subjectId) {
        validateInteger(subjectId, "Subject Id");
        if (!name) {
            throw new Error("Invalid Name");
        }
        this.name = name;
        this.subjectId = subjectId;
    }
}

export class CourseService {
    /**
     * 
     * @param {string} baseUrl invalid base URL will throw error, empty baseUrl is allowed
     */
    constructor(baseUrl) {
        if (baseUrl === "")
            this.baseUrl = baseUrl;
        else
            this.baseUrl = new URL(baseUrl).origin;
        this.courseEndPoint = this.baseUrl + "/api/course";
    }

    async getAllCourses() {
        return await fetch(this.courseEndPoint + "/all", { method: "get" });
    }

    /**
     * 
     * @param {number} id 
     */
    async getCourseById(id) {
        validateInteger(id, "id");
        return await fetch(this.courseEndPoint + "/" + id, { method: "get" });
    }

    /**
     * 
     * @param {number} page 
     * @param {number} limit 
     */
    async getCoursesOfPageLimit(page, limit) {
        validateInteger(page, "page");
        validateInteger(limit, "limit");
        return await fetch(`${this.courseEndPoint}?${new URLSearchParams({ page, limit })}`, { method: "get" });
    }

    /**
     * 
     * @param {Course} course 
     */
    async createCourse(course) {
        validateInteger(course.subjectId, "Subject Id");
        if (!course.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.courseEndPoint, {
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify(course), method: "post"
        });
    }

    /**
     * 
     * @param {number} id 
     * @param {Course} course 
     */
    async updateCourse(id, course) {
        validateInteger(course.subjectId, "Subject Id");
        validateInteger(id, "id");
        if (!course.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.courseEndPoint + "/" + id, {
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify(course), method: "put"
        });
    }
}