import React from 'react'
import ReactDOM from 'react-dom/client'

import "ag-grid-community/styles/ag-grid.css"; // Mandatory CSS required by the Data Grid
import "ag-grid-community/styles/ag-theme-quartz.css"; // Optional Theme applied to the Data Grid
import './index.css'

import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import ApplicationLayout from "./layout/applicationLayout.tsx";
import Dashboard from "./page/dashboard.tsx";


const router = createBrowserRouter([
    {
        path: "/",
        element: <ApplicationLayout/>,
        children: [
            {
                index: true,
                element: <Dashboard/>
            }
        ]
    },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>,
)
