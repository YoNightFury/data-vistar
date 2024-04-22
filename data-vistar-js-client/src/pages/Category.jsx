import { useState } from "react";
import { CategoryService } from "../services/CategoryService";
import { useEffect } from "react";
import { useParams } from "react-router-dom";

export default function Category(props) {
    const [category, setCategory] = useState({});
    const categoryId = useParams().id;
    const categoryService = new CategoryService("");
    useEffect(() => {
        categoryService.getCategoryById(categoryId).then(resp => resp.json()).then(resp => {
            if (resp.success!==false) {
                return resp;
            } else {
                throw new Error(resp.message);
            }
        }).then(setCategory).catch(alert);
    }, []);

    return (
        <><div>Name: {category.name}</div>
            <div>Id: {category.id}</div></>
    );

}