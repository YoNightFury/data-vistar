import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Categories from './pages/Categories';
import Category from './pages/Category';

function App() {
  return (
   <BrowserRouter>
    <Routes>
      <Route path='/categories' element={<Categories/>} />
      <Route path='/category/:id' element={<Category/>} />
      <Route path='/' element={<div>Hello</div>} />
    </Routes>
   </BrowserRouter>
  );
}

export default App;
