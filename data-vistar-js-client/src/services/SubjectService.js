import { validateInteger } from "./Validator.js";


export class Subject {
    /**
     * 
     * @param {string} name 
     * @param {number} categoryId 
     */
    constructor(name, categoryId) {
        validateInteger(categoryId, "Category Id");
        if (!name) {
            throw new Error("Invalid Name");
        }
        this.name = name;
        this.categoryId = categoryId;
    }
}

export class SubjectService {
    /**
     * 
     * @param {string} baseUrl invalid base URL will throw error, empty baseUrl is allowed
     */
    constructor(baseUrl) {
        if (baseUrl === "")
            this.baseUrl = baseUrl;
        else
            this.baseUrl = new URL(baseUrl).origin;
        this.subjectEndPoint = this.baseUrl + "/api/subject";
    }
    async getAllSubjects() {
        return await fetch(this.subjectEndPoint + "/all", { method: "get" });
    }
    /**
     * 
     * @param {number} id 
     */
    async getSubjectById(id) {
        validateInteger(id, "id");
        return await fetch(this.subjectEndPoint + "/" + id, { method: "get" });
    }
    /**
     * 
     * @param {number} page 
     * @param {number} limit 
     */
    async getSubjectsOfPageLimit(page, limit) {
        validateInteger(page, "page");
        validateInteger(limit, "limit");
        return await fetch(`${this.subjectEndPoint}?${new URLSearchParams({ page, limit })}`, { method: "get" });
    }

    /**
     * 
     * @param {Subject} subject 
     */
    async createSubject(subject) {
        validateInteger(subject.categoryId, "Category Id");
        if (!subject.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.subjectEndPoint, {
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify(subject), method: "post"
        });
    }
    async updateSubject(id, subject) {
        validateInteger(subject.categoryId, "Category Id");
        validateInteger(id);
        if (!subject.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.subjectEndPoint + "/" + id, {
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify(subject), method: "put"
        });
    }

}

