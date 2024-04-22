import { validateInteger } from "./Validator.js";

export class Topic {
    /**
     * 
     * @param {string} name 
     * @param {number} courseId 
     */
    constructor(name, courseId) {
        validateInteger(courseId, "Course Id");
        if (!name) {
            throw new Error("Invalid Name");
        }
        this.name = name;
        this.courseId = courseId;
    }
}

export class TopicService {
    /**
     * 
     * @param {string} baseUrl invalid base URL will throw error, empty baseUrl is allowed
     */
    constructor(baseUrl) {
        if (baseUrl === "") {
            this.baseUrl = baseUrl;
        } else {
            this.baseUrl = new URL(baseUrl).origin;
        }
        this.topicEndPoint = this.baseUrl + "/api/topic";
    }

    async getAllTopics() {
        return await fetch(this.topicEndPoint + "/all", { method: "get" });
    }

    /**
     * 
     * @param {number} id 
     */
    async getTopicById(id) {
        validateInteger(id, "id");
        return await fetch(this.topicEndPoint + "/" + id, { method: "get" });
    }

    /**
     * 
     * @param {number} page 
     * @param {number} limit 
     */
    async getTopicsOfPageLimit(page, limit) {
        validateInteger(page, "page");
        validateInteger(limit, "limit");
        return await fetch(`${this.topicEndPoint}?${new URLSearchParams({ page, limit })}`, { method: "get" });
    }

    /**
     * 
     * @param {Topic} topic 
     */
    async createTopic(topic) {
        validateInteger(topic.courseId, "Course Id");
        if (!topic.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.topicEndPoint, {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(topic),
            method: "post"
        });
    }

    /**
     * 
     * @param {number} id 
     * @param {Topic} topic 
     */
    async updateTopic(id, topic) {
        validateInteger(topic.courseId, "Course Id");
        validateInteger(id, "id");
        if (!topic.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.topicEndPoint + "/" + id, {
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(topic),
            method: "put"
        });
    }
}
