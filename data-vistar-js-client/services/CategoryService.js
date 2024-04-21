import { validateInteger } from "./Validator.js";

export class Category {
    /**
     * 
     * @param {string} name 
     */
    constructor(name) {
        if (!name) {
            throw new Error("Invalid Name");
        }
        this.name = name;
    }
}

export class CategoryService {
    /**
     * 
     * @param {string} baseUrl invalid base URL will throw error, empty baseUrl is allowed
     */
    constructor(baseUrl) {
        if (baseUrl === "")
            this.baseUrl = baseUrl;
        else
            this.baseUrl = new URL(baseUrl).origin;
        this.categoryEndPoint = this.baseUrl + "/api/category";
    }
    async getAllCategories() {
        return await fetch(this.categoryEndPoint + "/all", { method: "get" });
    }
    /**
     * 
     * @param {number} id 
     */
    async getCategoryById(id) {
        validateInteger(id, "id");
        return await fetch(this.categoryEndPoint + "/" + id, { method: "get" });
    }
    /**
     * 
     * @param {number} page 
     * @param {number} limit 
     */
    async getCategoriesOfPageLimit(page, limit) {
        validateInteger(page, "page");
        validateInteger(limit, "limit");
        return await fetch(`${this.categoryEndPoint}?${new URLSearchParams({ page, limit })}`, { method: "get" });
    }

    /**
     * 
     * @param {Category} category 
     */
    async createCategory(category) {
        if (!category.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.categoryEndPoint, {
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify(category), method: "post"
        });
    }
    async updateCategory(id, category) {
        validateInteger(id);
        if (!category.name) {
            throw new Error("Invalid name");
        }
        return await fetch(this.categoryEndPoint + "/" + id, {
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify(category), method: "put"
        });
    }

}
