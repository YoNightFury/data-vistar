import { useState } from "react";
import { Category, CategoryService } from "../services/CategoryService";
import { useEffect } from "react";
import { Link } from "react-router-dom";

export default function Categories(props) {
    const [categories, setCategories] = useState([]);
    const categoryService = new CategoryService("");
    useEffect(() => {
        categoryService.getAllCategories().then(resp => resp.json()).then((categories) => setCategories(categories));
    }, []);

    return (<table>
        {categories.map((category) =>
            <tr>
                <td>
                    {category.id}
                </td>
                <td>
                    <Link to={"/category/"+category.id} >
                        {category.name}
                    </Link>
                </td>
            </tr>
        )}
    </table>);

}