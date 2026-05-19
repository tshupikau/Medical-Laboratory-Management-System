import { useEffect, useState } from "react"
import type { Patient } from "../../types/patient"

export default function PatientsPage() {
    const [patients, setPatients] = useState<Patient[]>([])
    const [search, setSearch] = useState("")
    
    useEffect(() => {
    fetch("http://localhost:8080/api/patients")
      .then((res) => res.json())
      .then((data) => setPatients(data))
    }, [])
    
    const filteredPatients = patients.filter((p) =>
        p.fullName.toLowerCase().includes(search.toLowerCase())
    )

    console.log(patients)
    
    return (
    <div className="min-h-screen bg-zinc-950 flex items-center justify-center p-6">
        <div className="w-full max-w-md">
            <div className="relative">
                <div>
                    <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="absolute left-3 top-1/2 h-5 w-5 -translate-y-1/2 text-zinc-400"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    stroke-width="2"
                    >
                        <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            d="M21 21l-4.35-4.35m1.85-5.15a7 7 0 11-14 0 7 7 0 0114 0z"
                        />
                    </svg>
                    <input
                        type="text"
                        onChange={(e) => setSearch(e.target.value)}
                        placeholder="Search..."
                        className="w-full rounded-2xl border border-zinc-700 bg-zinc-900 px-4 py-3 pl-11 text-white placeholder-zinc-400 outline-none transition focus:border-blue-500"
                    />
                </div>
                

                <div>
                    {filteredPatients.map((patient) => (
                        <div
                        key={patient.id}
                        className="bg-zinc-900 border border-zinc-800 rounded-2xl p-5"
                        >
                        <h2 className="text-xl font-semibold">
                            {patient.fullName}
                        </h2>

                        <p className="text-zinc-400">
                            Age: {patient.birthDate}
                        </p>

                        <p className="text-zinc-500 text-sm mt-2">
                            ID: {patient.id}
                        </p>
                        </div>
                    ))}
                </div>

            </div>
        </div>
    </div>
    )
}

