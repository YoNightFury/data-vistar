import { validateInteger } from './Validator.js';

export class Content {
    /**
     * 
     * @param {string} title 
     * @param {string} body 
     * @param {number} topicId 
     */
    constructor(title, body, topicId) {
        validateInteger(topicId, "Topic Id");
        if (!title) {
            throw new Error("Invalid Title");
        }
        this.title = title;
        this.body = body || "";
        this.topicId = topicId;
    }
}


export class ContentService {
    /**
     * 
     * @param {string} baseUrl invalid base URL will throw error, empty baseUrl is allowed
     */
    constructor(baseUrl) {
        if (baseUrl === "")
            this.baseUrl = baseUrl;
        else
            this.baseUrl = new URL(baseUrl).origin;
        this.contentEndPoint = this.baseUrl + "/api/content";
    }

    async getAllContents() {
        return await fetch(this.contentEndPoint + "/all", { method: "get" });
    }

    /**
     * 
     * @param {number} id 
     */
    async getContentById(id) {
        validateInteger(id, "id");
        return await fetch(this.contentEndPoint + "/" + id, { method: "get" });
    }

    /**
     * 
     * @param {number} page 
     * @param {number} limit 
     */
    async getContentsOfPageLimit(page, limit) {
        validateInteger(page, "page");
        validateInteger(limit, "limit");
        return await fetch(`${this.contentEndPoint}?${new URLSearchParams({ page, limit })}`, { method: "get" });
    }

    /**
     * 
     * @param {Content} content 
     */
    async createContent(content) {
        validateInteger(content.topicId, "Topic Id");
        if (!content.title) {
            throw new Error("Invalid title");
        }
        return await fetch(this.contentEndPoint, {
            headers: {
                "Content-Type": "application/json"
            }, 
            body: JSON.stringify(content), 
            method: "post"
        });
    }

    /**
     * 
     * @param {number} id 
     * @param {Object} content 
     */
    async updateContent(id, content) {
        validateInteger(content.topicId, "Topic Id");
        validateInteger(id, "id");
        if (!content.title) {
            throw new Error("Invalid title");
        }
        return await fetch(this.contentEndPoint + "/" + id, {
            headers: {
                "Content-Type": "application/json"
            }, 
            body: JSON.stringify(content), 
            method: "put"
        });
    }
}
