
import Dashboard from './Pages/Dashboard/Dashboard'
import NotFound from './Pages/NotFound'
import { Routes,Route } from 'react-router-dom'

function App() {

  return (
    <Routes>
      <Route path = "/" element = {<Dashboard/>} />
      <Route path = "*" element = {<NotFound/>} />
    </Routes>
  )
}

export default App
